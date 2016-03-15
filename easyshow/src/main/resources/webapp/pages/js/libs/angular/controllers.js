'use strict';

var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', 'firstScanFactory', 'newDBFactory',
		function($scope, firstScanFactory, newDBFactory) {
			firstScanFactory.get({}, function(firstScanFactory) {
				$scope.shows = firstScanFactory.listShows;
			})
			$scope.isScan = true;
			$scope.isHome = false;

			$scope.callJersey = function(shows) {
				var scannedShows = [];
				var show;
				for (show in shows) {
					var temp = {};
					console.log(show);
					console.log(shows[show].selectedShow);
					temp.name = show;
					temp.properties = shows[show].selectedShow;
					scannedShows.push(temp);
				}
				console.log(scannedShows);
				newDBFactory.save({
					"scannedShows" : scannedShows
				});
			}
		} ]);