'use strict';
var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', '$resource', '$http', '$q', '$log', '$timeout', 'scanFactory', 'addShowsFactory',
		function($scope, $resource, $http, $q, $log, $timeout, scanFactory, addShowsFactory) {
			scanFactory.get({}, function(scanFactory) {
				$scope.shows = scanFactory.listShows;
			});
			$scope.isScan = true;
			$scope.isHome = false;

			$scope.addShowsToBDD = function(shows) {
				var selectedShows = [];
				var show;
				for (show in shows) {
					var temp = {};
					temp.name = show;
					temp.properties = shows[show].selectedShow;
					selectedShows.push(temp);
				}
				$http.post('/easyshow/api/shows/add', selectedShows).then(
						function successCallback(data){
							window.location="home.html";
						}, function errorCallback(data){
							alert("Erreur lors de l'ajout des séries en bdd.");
						});
			}
		} ]);

app.controller('homeController', [ '$scope', 'getShowsFactory',
       function($scope, getShowsFactory) {
		getShowsFactory.get({}, function(getShowsFactory) {
			$scope.shows = getShowsFactory.listShows;
		});
		
		$scope.isScan = false;
		$scope.isHome = true;
	} ]);