angular.module('app', [ 'ngRoute' ])
	.controller('MatchesCtrl',
		function($scope, $http, $location) {
			$http.get('rest/matches').success(function(data) {
				$scope.matches = data.match;
			});

			$http.get('rest/teams').success(function(data) {
				$scope.teams = data.team;
			});

			$scope.createMatch = function() {
				var match = {
					"match" : {
						"team1" : $scope.team1,
						"team2" : $scope.team2,
						"result" : {
							"team1Score" : $scope.team1Score,
							"team2Score" : $scope.team2Score
						},
						"matchDate" : $scope.matchDate
					}
				};
				$http.post('rest/matches', match).success(function() {
					$location.path('/matches');
				});
			};
		})
	.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/matches', {
				templateUrl: "views/allmatches.html",
				controller: "MatchesCtrl"
			})
			.when('/matches/create', {
				templateUrl: "views/creatematch.html",
				controller: "MatchesCtrl"
			})
			.otherwise({
				redirectTo: "/matches"
			});
	}]);