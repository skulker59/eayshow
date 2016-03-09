'use strict';

var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', 'firstScanFactory', 'testFactory',
		function($scope, firstScanFactory, testFactory) {
			firstScanFactory.get({}, function(firstScanFactory) {
				$scope.shows = firstScanFactory.listShows;
			})
			$scope.isScan = true;
			$scope.isHome = false;

			$scope.callJersey = function(shows) {
				var show;
				for (show in shows) {
					console.log(show);
					console.log(shows[show].selectedShow);
				}

				testFactory.save({
					"param" : "toto"
				});
			}
		} ]);