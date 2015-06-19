var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
		.when('/', {templateUrl: 'partials/connect.jsp', controller: 'connectCtrl'})
		.when('/menu', {templateUrl: 'partials/menu.html', controller: 'menuCtrl'})
		.when('/ousuisje', {templateUrl: 'partials/ousuisje.html', controller: 'ousuisjeCtrl'})
		.otherwise({redirectTo: '/'});
});
