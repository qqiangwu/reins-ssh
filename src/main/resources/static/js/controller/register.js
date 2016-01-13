(function(module){
    'use strict';

    module.controller('RegisterCtrl', ['$scope', 'Auth',
        function($scope, Auth){
            $scope.email = null;
            $scope.username = null;
            $scope.password = null;

            $scope.register = function(){
                Auth.register({
                    email: $scope.email,
                    name: $scope.username,
                    password: $scope.password
                }).then(function(){
                    $scope.report({
                        message: 'Register accomplished!',
                        timeout: 2,
                        url: '/'
                    });
                }).catch(function(ec){
                    $scope.report({
                        message: ec.msg,
                        timeout: 2
                    });
                });
            };
        }
    ]);
})(angular.module('miao.controller'));