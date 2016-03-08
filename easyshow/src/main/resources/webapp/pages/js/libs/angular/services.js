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

services.factory('testFactory', function ($resource) {
    return $resource('/easyshow/api/folders/angu/:param', {}, {
        save: {
            method: 'POST',
            params: {param : '@param'}
//            isArray: false
        }
    })
});