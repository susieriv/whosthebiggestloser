app.controller('menuCtrl', ['$scope', 'agendaSrv', '$window', function($scope, agendaSrv, $window) {
	
	$scope.showprofil = false;
	$scope.showinterest = false;
	$scope.showFAQ = false;
	$scope.showagenda = false;
	$scope.showranking = false;
	$scope.greyscreen = false;
		
	$scope.close = function() {$scope.showprofil = false;$scope.showinterest = false;$scope.showFAQ = false;$scope.showagenda = false;$scope.showranking = false;$scope.greyscreen = false;}

	/*-----------------------------*\
	|       PARTIE PROFIL           |
	\*-----------------------------*/
	$scope.profil = function() {$scope.showprofil = true; $scope.greyscreen = true;}

	/*-----------------------------*\
	|       PARTIE INTEREST         |
	\*-----------------------------*/	
	$scope.style = [ {"label" : "agriculture, environnement"},
	                 {"label" : "animation loisirs jeunes"},
	                 {"label" : "peinture, dessin, théâtre"},
	                 {"label" : "autres scènes"},
	                 {"label" : "sortie, voyage"},
	                 {"label" : "cinéma"},
	                 {"label" : "concert, spectacle musical"}];
	$scope.stylebis = [ {"label" : "conférence"},
	                 {"label" : "danser"},
	                 {"label" : "dédicace / livre"},
	                 {"label" : "exposition, musée"},
	                 {"label" : "visite de monuments, parcs, zoo ..."},
	                 {"label" : "festival"},
	                 {"label" : "fêtes"}];
	$scope.styleter = [ {"label" : "formation, vie scolaire, examens, concours"},
	                 {"label" : "gala, spectacle de fin d année"},
	                 {"label" : "jeux, concours, rallye"},
	                 {"label" : "multimédia"},
	                 {"label" : "sport"},
	                 {"label" : "repas soirée"},
	                 {"label" : "réunions, conseils, assemblées générales"}];
	$scope.interest = function() {$scope.showinterest = true; $scope.greyscreen = true;}
	$scope.modifinteret = function() {$scope.showinterest = false; $scope.greyscreen = false;}

	/*-----------------------------*\
	|       PARTIE RANKING          |
	\*-----------------------------*/
	$scope.ranking = function() {$scope.showranking = true; $scope.greyscreen = true;}

	/*-----------------------------*\
	|       PARTIE FAQ              |
	\*-----------------------------*/
	$scope.FAQ = function() {
		$scope.showFAQ = true; 
		$scope.greyscreen = true;
		var rootApi = 'https://1-dot-whosthebiggestloser.appspot.com/_ah/api/';
			gapi.client.load('questionendpoint', 'v1', function() {
		    gapi.client.questionendpoint.listQuestion().execute(
		    function(resp) {
		    	$scope.questions = resp.items;
		        $scope.$apply();
		    });
		}, rootApi);
	}	
	
	$scope.question;   
	$scope.formfaq = false;
	$scope.showformfaq = function() {$scope.formfaq = true;};

    $scope.newfaq = function() {
		$scope.formfaq = false;
		console.log($scope.question);
		var rootApi = 'https://1-dot-whosthebiggestloser.appspot.com/_ah/api/';
		gapi.client.load('questionendpoint', 'v1', function() {
			gapi.client.questionendpoint.insertQuestion({
		    id: Math.floor((Math.random() * 100) + 1),
		    titre:$scope.question,
		    }).execute();
		}, rootApi);
	}
	
	/*-----------------------------*\
	|       PARTIE AGENDA           |
	\*-----------------------------*/
	$scope.agenda = function() {
		$scope.showagenda = true; 
		$scope.greyscreen = true;
		$scope.jsonGet = 'http://datainfolocale.opendatasoft.com/api/records/1.0/search?dataset=agenda_culturel&facet=facette_debut&facet=rubrique&facet=nav_lieu&facet=organisme_nom&facet=organisme_sous_type&refine.nav_lieu=44';
		agendaSrv.get($scope.jsonGet).then(function(evenement){
			$scope.events = evenement;
			console.log($scope.events);
			$scope.event = $scope.events.records;
			console.log($scope.event);
		}, function(msg) {
			alert(msg);
		});
		
	
	
}}]);