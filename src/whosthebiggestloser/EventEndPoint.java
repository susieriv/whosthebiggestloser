package whosthebiggestloser;

import whosthebiggestloser.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class EventEndPoint {
	@Api(name = "eventendpoint", namespace = @ApiNamespace(ownerDomain = "mycompany.com", ownerName = "mycompany.com", packagePath = "services"))
	public class EventEndpoint {

		/**This method lists all the entities inserted in datastore.*/
		@SuppressWarnings({ "unchecked", "unused" })
		@ApiMethod(name = "listEvent")
		public CollectionResponse<Event> listEvent(
				@Nullable @Named("cursor") String cursorString,
				@Nullable @Named("limit") Integer limit) {

			PersistenceManager mgr = null;
			Cursor cursor = null;
			List<Event> execute = null;

			try {
				mgr = getPersistenceManager();
				Query query = mgr.newQuery(Event.class);
				if (cursorString != null && cursorString != "") {
					cursor = Cursor.fromWebSafeString(cursorString);
					HashMap<String, Object> extensionMap = new HashMap<String, Object>();
					extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
					query.setExtensions(extensionMap);
				}
				if (limit != null) {query.setRange(0, limit);}
				execute = (List<Event>) query.execute();
				cursor = JDOCursorHelper.getCursor(execute);
				if (cursor != null)
					cursorString = cursor.toWebSafeString();
				for (Event obj : execute);
			} finally {mgr.close();}
			return CollectionResponse.<Event> builder().setItems(execute)
					.setNextPageToken(cursorString).build();
		}

		/** This method gets the entity having primary key id.
		 * @param id the primary key of the java bean.
		 * @return The entity with primary key id. */
		@ApiMethod(name = "getEvent")
		public Event getEvent(@Named("id") Long id) {
			PersistenceManager mgr = getPersistenceManager();
			Event event = null;
			try {event = mgr.getObjectById(Event.class, id);} 
			finally {mgr.close();}
			return event;
		}

		/**This inserts a new entity into App Engine datastore. 
		 * @param event the entity to be inserted.
		 * @return The inserted entity.*/
		@ApiMethod(name = "insertEvent")
		public Event insertEvent(Event event) {
			PersistenceManager mgr = getPersistenceManager();
			try {
				if (containsEvent(event)) {throw new EntityExistsException("Object already exists");}
				mgr.makePersistent(event);
			} finally {mgr.close();}
			return event;
		}

		/**This method is used for updating an existing entity.
		 * @param event the entity to be updated.
		 * @return The updated entity. */
		@ApiMethod(name = "updateEvent")
		public Event updateEvent(Event event) {
			PersistenceManager mgr = getPersistenceManager();
			try {
				if (!containsEvent(event)) {throw new EntityNotFoundException("Object does not exist");}
				mgr.makePersistent(event);
			} finally {mgr.close();}
			return event;
		}

		/**This method removes the entity with primary key id.
		 * @param id the primary key of the entity to be deleted. */
		@ApiMethod(name = "removeEvent")
		public void removeEvent(@Named("id") Long id) {
			PersistenceManager mgr = getPersistenceManager();
			try {
				Event event = mgr.getObjectById(Event.class, id);
				mgr.deletePersistent(event);
			} finally {mgr.close();}
		}

		private boolean containsEvent(Event event) {
			PersistenceManager mgr = getPersistenceManager();
			boolean contains = true;
			try {mgr.getObjectById(Event.class, event.getId());} 
			catch (javax.jdo.JDOObjectNotFoundException ex) {contains = false;} 
			finally {mgr.close();}
			return contains;
		}
		
		/* AVANT : private static PersistenceManager
		 * Je ne sais pas pourquoi il n'en veut pas	 */
		private PersistenceManager getPersistenceManager() {
			return PMF.get().getPersistenceManager();
		}
	}
}


