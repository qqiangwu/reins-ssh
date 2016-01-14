(function(){
    'use strict';

    //! define all modules
    angular.module('miao.util', []);
    angular.module('miao.filter', []);
    angular.module('miao.router', ['ngRoute', 'miao.controller', 'miao.service']);
    angular.module('miao.resource', ['ngResource', 'miao.service']);
    angular.module('miao.controller', ['miao.resource']);
    angular.module('miao.service', ['LocalStorageModule']);
    angular.module('miao.directive', []);
    angular.module('miao.tpl', []);

    var app = angular.module('miao', [
        'ui.bootstrap',
        'ngSanitize',
        'LocalStorageModule',
        'btford.markdown',
        'miao.filter', 'miao.directive', 'miao.util', 'miao.router',
        'miao.resource', 'miao.service', 'miao.tpl'
    ]);

    app.constant('AppMeta', {
        name: 'miao.Blog',
        version: 'v0.0.1'
    });

    app.config(['$httpProvider', function($httpProvider) {
            $httpProvider.interceptors.push('Monitor.responseFilter');
            $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
            $httpProvider.defaults.transformRequest = function(req) {
                var str = [];
                for(var p in req) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(req[p]));
                }
                return str.join("&");
            };
        }
    ]);
    
    app.config(['localStorageServiceProvider', function(localStorageServiceProvider){
        localStorageServiceProvider.setPrefix('miao');
    }]);

    app.run(['$rootScope', '$location', '$log',
        function($rootScope, $location, $log){
            $rootScope.log = $log;

            $rootScope.itemsPerPage = 5;
            $rootScope.maxPgSize = 7;

            $rootScope.go = function(url){
                $location.path(url || '/');
            };
        }
    ]);

    app.run(['$rootScope', '$modal',
        function($rootScope, $modal){
            $rootScope.report = function(option){
                var result = $modal.open({
                    templateUrl: 'tpl/modal.html',
                    controller: 'ModalInstanceCtrl',
                    size: 'sm',
                    resolve: {
                        option: function(){
                            return option;
                        }
                    }
                }).result;

                if (option.url) {
                    result.finally(function(){
                        $rootScope.go(option.url);
                    });
                }
            };
        }
    ]);

    app.run(['$rootScope', 'Auth', function($root, Auth){
        $root.$on('auth:login', function(_, user){
            $root.log.info('login');
            $root.hasLogin = true;
            $root.user = user;
        });

        $root.$on('auth:logout', function(){
            $root.log.info('logout');
            $root.hasLogin = false;
            $root.user = null;
        });

        $root.logout = function() {
            Auth.logout().then(function(){
                $root.report({
                    message: 'Logout successfully',
                    timeout: 2,
                    url: '/'
                });
            });
        };

        var user = Auth.user();

        if (user) {
            $root.log.info('app init login');
            $root.user = user;
            $root.hasLogin = true;
        }
    }]);
})();