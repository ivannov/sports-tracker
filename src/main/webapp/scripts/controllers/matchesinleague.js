angular.module('controllers.matchesinleague', [])
	.controller('MatchesInLeagueCtrl',
			function($scope, $http, $route) {
		$http.get('rest/leagues/' + $route.current.params.leagueId + '/matches').success(function(data) {
			$scope.matches = data.match;
		});
	});
