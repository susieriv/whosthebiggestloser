app.service('agendaSrv', ['$http', '$q', function($http, $q) {
	
	var donnee = {};
	
	this.get = function (url) {
		var deferred = $q.defer();
			$http.get(url)
			  .success(function(data, status) {
			    donnee = data;
			    deferred.resolve(donnee);
			  })
			  .error(function(data, status) {
				  deferred.reject('Donnees non accessibles');
			  });
		return deferred.promise;	
	}
 }]);
		