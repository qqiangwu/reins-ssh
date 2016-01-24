(function(){
    'use strict';

    // @require /import/angular/angular.js
    // @require /import/angular-route/angular-route.js
    // @require pages

    var router = angular('boot.router', ['ngRoute', 'pages']);

    router.config(function($routeProvider, PageDef){
        angular.each(PageDef, function(item){
            $routeProvider.when(item.url, item.config);
        });

        $router.otherwise('/main');
    });
})();
