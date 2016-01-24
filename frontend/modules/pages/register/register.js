(function(module){
    'use strict';

    module.controller('RegisterCtrl', ['$scope', 'Auth',
        function($scope, Auth){
            $scope.user = {};

            $scope.register = function(){
                if (!$scope.userForm.$valid) {
                    $scope.report({
                        message: 'Invalid Input'
                    });
                } else {
                    Auth.register($scope.user).then(function(){
                        $scope.report({
                            message: 'Register accomplished!',
                            timeout: 2,
                            url: '/'
                        });
                    }).catch(function(ec){
                        $scope.report({
                            message: ec.message,
                            timeout: 2
                        });
                    });
                }
            };
        }
    ]);

    return {
        url: '/register',
        config: {
            template: __inline('./register.html'),
            controller: 'RegisterCtrl'
        }
    };
})(angular.module('pages'));
