angular.module('app', [ 'ngRoute', 'controllers.leagues', 'controllers.matchesinleague', 
                        'controllers.matches' ])
	.config(['$routeProvider', function($routeProvider) {
    	$routeProvider.when('/leagues', {
			templateUrl: "views/allleagues.html",
			controller: "LeaguesCtrl"
		})
		.when('/leagues/:leagueId', {
			templateUrl: "views/matchesinleague.html",
			controller: "MatchesInLeagueCtrl"
		})
		.when('/matches/create', {
			templateUrl: "views/creatematch.html",
			controller: "MatchesCtrl"
		})
		.otherwise({
			redirectTo: "/leagues"
		});
    }]);