'use strict';
var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', '$resource', '$http', '$q', '$log', '$timeout', 'scanFactory', 'addShowsFactory',
		function($scope, $resource, $http, $q, $log, $timeout, scanFactory, addShowsFactory) {
			scanFactory.get({}, function(scanFactory) {
				$scope.shows = scanFactory.listShows;
			});
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
//				addShowsFactory.save(selectedShows);
//				var promise = addShowsFactory.save(selectedShows);
//				console.log(promise);
//				promise.then(
//				  function() {
//				    alert("Yeah !!");
//				  });
//				function testPromise() {
//					  var deferred = $q.defer();
//		
//					  addShowsFactory.save(selectedShows, function(addShowsFactory) {
//						  alert(addShowsFactory.value);
//						  });
//					  deferred.resolve('test');
//		
//					  return deferred.promise;
//				}
//				var promise = testPromise();
//				promise.then(function(shows) {
//			      alert("Yeah !")
//			      },function(){
//			    	  alert('error');
//			      });
				$http.post('/easyshow/api/shows/add', selectedShows).then(
						function(data){
							alert("Yes");
						}, function(data){
							alert("No");
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