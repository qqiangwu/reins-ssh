(function(router){
    'use strict';

    // @require /import/angular/angular.js
    // @require /import/angular-route/angular-route.js
    // @require pages

    router.config(function($routeProvider, PageDef){
        "ngInject";

        PageDef.forEach(function(item){
            $routeProvider.when(item.url, item.config);
        });

        $routeProvider.otherwise('/main');
    });
})(angular.module('boot.router', ['ngRoute', 'pages']));
