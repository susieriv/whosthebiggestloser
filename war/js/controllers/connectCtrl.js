app.controller('connectCtrl', ['$scope', '$location', function($scope, $location) {

	$scope.menu = function() {$location.path('/menu');}
	
	$scope.ousuisje = function() {$location.path('/ousuisje');}
	
}]);