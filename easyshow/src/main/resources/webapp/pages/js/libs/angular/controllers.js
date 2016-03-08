'use strict';

var app = angular.module('easyshow.controllers', []);

app.controller('scanController', ['$scope', 'firstScanFactory', 'testFactory', function ($scope, firstScanFactory, testFactory) {
	firstScanFactory.get({}, function (firstScanFactory) {
        $scope.shows = firstScanFactory.listShows;
    })
    $scope.isScan = true;
	$scope.isHome = false;
	
	$scope.callJersey = function() {
		testFactory.save({"param" : "toto"});
	}
}]);