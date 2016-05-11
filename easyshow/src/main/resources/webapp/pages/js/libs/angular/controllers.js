'use strict';
var app = angular.module('easyshow.controllers', []);

app.controller('scanController', [ '$scope', '$resource', '$http', '$q', '$log', '$timeout', 'scanFactory', 'addShowsFactory',
		function($scope, $resource, $http, $q, $log, $timeout, scanFactory, addShowsFactory) {
			$scope.shows = null;
			scanFactory.get({}, function(scanFactory) {
				$scope.shows = scanFactory.listShows;
				var modal = document.getElementById('myModal');
				modal.style.display = "none";
			});
			$scope.isScan = true;
			$scope.isHome = false;

			$scope.addShowsToBDD = function(shows) {
				var modal = document.getElementById('myModal');
				modal.style.display = "block";
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
							var modal = document.getElementById('myModal');
							modal.display.style = "none";
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

app.controller('showController', [ '$scope', '$http', '$location',
   function($scope, $http, $location) {
	$scope.url = $location.path();
	$scope.query = $location.search().id;
	
	$http({
	    url: '/easyshow/api/shows/getShow', 
	    method: "GET",
	    params: {id: $location.search().id}
	 }).then(
			function successCallback(data){
				alert('Success');
				$scope.show = data.data;
			}, function errorCallback(data){
				alert('Error');
			});
	
	$scope.isScan = false;
	$scope.isHome = false;
} ]);