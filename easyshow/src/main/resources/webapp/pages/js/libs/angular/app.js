'use strict';

(function() {
	angular.module('easyshow-app', ['easyshow.services', 'easyshow.controllers'], function($locationProvider){
//	    $locationProvider.html5Mode(true);
	    $locationProvider.html5Mode({
	    	  enabled: true,
	    	  requireBase: false
	    	});
	});
})();