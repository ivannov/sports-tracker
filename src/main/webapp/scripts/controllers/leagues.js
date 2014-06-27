angular.module('controllers.leagues', [])
	.controller('LeaguesCtrl',
			function($scope, $http) {
		$http.get('rest/leagues/year/2015').success(function(data) {
			$scope.leagues = data.league;
		});

	});