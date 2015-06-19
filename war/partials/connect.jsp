<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.*" %>

<% UserService userService = UserServiceFactory.getUserService(); %>

<!DOCTYPE html>

<html>

	<div class="logodiv"></div>

	<div id="centrer">

		<%-- PETIT MESSAGE CHANGEANT SELON CONNEXION OU NON --%>
		<div class="msgdiv">
			<% if (userService.getCurrentUser() != null) { %>
				Bonjour <%= userService.getCurrentUser().getNickname() %> ! <br> &bull; <a href="<%= userService.createLogoutURL("/") %>">se déconnecter</a> &bull;
        	<% } 
			else { %> N'hésite plus et rejoins nous ! <% } %>
		</div>

		<div id="btnaccueil">
			<%-- SELON CONNEXION OU NON : ACCES AU MENU OU ACCES AUX REGLES --%>
			<% if (userService.getCurrentUser() != null) { %>
				<button ng-click="menu()">Je suis déjà </br> un loser</button>
			<% } 
			else { %>
				<button ng-click="ousuisje()">Qu'est ce que </br> ce site ?</button>
			<% } %>

			<%-- BOUTON QUI PERMET DE SE CONNECTER SI NON CONNECTE --%>
			<% if (userService.getCurrentUser() == null) { %>
				<a href="<%= userService.createLoginURL("/") %>">
					<button>Je veux devenir </br>un loser</button>
				</a>
			<% } %>
		</div>
	
		<div class="footer">© Nolwenn Bellégo, Susie Rivière & Totoro</div>
	</div>

</html>