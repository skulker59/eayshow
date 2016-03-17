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
					temp.name = show;
					temp.properties = shows[show].selectedShow;
					selectedShows.push(temp);
				}
				addShowsFactory.save(selectedShows);
			}
		} ]);