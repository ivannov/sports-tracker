angular.module('app', [])
	.controller('MatchesCtrl', function($scope, $http) {
		$http.get('rest/matches').success(function(data) {
			$scope.matches = data.match;
		});
	});