/**
 * Created by Float on 14-8-22.
 */
(function(module){
    'use strict';

    module.config(['$routeProvider',
        function($routeProvider){
            $routeProvider
                .when('/main', {
                    templateUrl: 'tpl/main.html',
                    controller: 'MainCtrl'
                })
                .when('/login', {
                    templateUrl: 'tpl/login.html',
                    controller: 'LoginCtrl'
                })
                .when('/register', {
                    templateUrl: 'tpl/register.html',
                    controller: 'RegisterCtrl'
                })
                .when('/self', {
                    templateUrl: 'tpl/selfIntro.html'
                })
                .when('/dashboard', {
                    templateUrl: 'tpl/dashboard.html',
                    controller: 'DashboardCtrl'
                })
                .when('/blogView/:id', {
                    templateUrl: 'tpl/blogView.html',
                    controller: 'BlogViewCtrl'
                })
                .when('/blogEdit/:id', {
                    templateUrl: 'tpl/blogEdit.html',
                    controller: 'BlogEditCtrl'
                })
                .when('/blogCreate', {
                    templateUrl: 'tpl/blogCreate.html',
                    controller: 'BlogCreateCtrl'
                })
                .otherwise({
                    redirectTo: '/main'
                });
        }
    ]);
})(angular.module('miao.router'));