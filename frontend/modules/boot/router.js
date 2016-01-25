(function(router){
    'use strict';

    // @require angular
    // @require angular-route
    // @require pages

    router.config(['$routeProvider', 'PageDef', function($routeProvider, PageDef){
        "ngInject";

        PageDef.forEach(function(x){
            $routeProvider.when(x.url, x.config);
        });

        $routeProvider.otherwise('/main');
    }]);
})(angular.module('boot.router', ['ngRoute', 'pages']));
