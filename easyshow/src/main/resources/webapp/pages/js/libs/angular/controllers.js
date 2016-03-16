'use strict';
var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', 'scanFactory', 'addShowsFactory',
		function($scope, scanFactory, addShowsFactory) {
			scanFactory.get({}, function(scanFactory) {
				$scope.shows = scanFactory.listShows;
			})
			$scope.isScan = true;
			$scope.isHome = false;

			$scope.callJersey = function(shows) {
				var selectedShows = [];
				var show;
				for (show in shows) {
					var temp = {};
					console.log(show);
					console.log(shows[show].selectedShow);
					temp.name = show;
					temp.properties = shows[show].selectedShow;
					selectedShows.push(temp);
				}
				console.log(selectedShows);
				addShowsFactory.save(selectedShows);
			}
		} ]);