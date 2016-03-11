var services = angular.module('easyshow.services', ['ngResource']);

services.factory('firstScanFactory', function ($resource) {
    return $resource('/easyshow/api/folders/scan', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: true
        }
    })
});

services.factory('newDBFactory', function ($resource) {
    return $resource('/easyshow/api/folders/angu', {}, {
        save: {
            method: 'POST',
            params: {scannedShows : '@scannedShows'}
//            isArray: false
        }
    })
});