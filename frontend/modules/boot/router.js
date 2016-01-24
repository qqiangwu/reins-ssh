(function(router){
    'use strict';

    // @require /import/angular/angular.js
    // @require /import/angular-route/angular-route.js
    // @require pages

    router.config(['$routeProvider', 'PageDef', function($routeProvider, PageDef){
        "ngInject";

        PageDef.forEach(function(x){
            $routeProvider.when(x.url, x.config);
        });

        $routeProvider.otherwise('/main');
    }]);
})(angular.module('boot.router', ['ngRoute', 'pages']));
