
(function(module){
    'use strict';

    module.controller('LoginCtrl', ['$scope', 'Auth', function($scope, Auth){
        $scope.login = function(){
            var token = $scope.token;
            if (!token.email) {
                $scope.report({
                    message: 'Invalid email',
                    timeout: 1
                });
            } else if (!token.password) {
                $scope.report({
                    message: 'Invalid password',
                    timeout: 1
                });
            } else {
                Auth.login(token.email, token.password)
                    .then(function(){
                        $scope.report({
                            message: 'Login accomplished!',
                            timeout: 2,
                            url: '/'
                        });
                    })
                    .catch(function(ec, status) {
                        $scope.report({
                            message: 'Errant in either email or password:' + ec.msg,
                            timeout: 2
                        });
                    });
            }
        };
    }]);

    return {
        url: '/login',
        config: {
            template: __inline('./login.html'),
            controller: 'LoginCtrl'
        }
    };
})(angular.module('pages'));
