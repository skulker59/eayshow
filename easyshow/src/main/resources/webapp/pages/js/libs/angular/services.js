var services = angular.module('easyshow.services', ['ngResource']);

services.factory('scanFactory', function ($resource) {
    return $resource('/easyshow/api/datasystem/scan', {}, {
        query: {
            method: 'GET'
        }
    })
});

services.factory('addShowsFactory', function ($resource) {
    return $resource('/easyshow/api/shows/add', {}, {
        save: {
            method: 'POST',
            isArray: true
        }
    })
});

services.factory('getShowsFactory', function ($resource) {
    return $resource('/easyshow/api/shows/get/all', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: true
        }
    })
});

services.factory('getShowFactory', function ($resource) {
    return $resource('/easyshow/api/shows/get/all', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: true
        }
    })
});