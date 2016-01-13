
(function(module){
    'use strict';

    module.controller('LoginCtrl', ['$scope', 'Auth',
        function($scope, Auth){
            $scope.email = null;
            $scope.password = null;

            $scope.login = function(){
                Auth.login($scope.email, $scope.password)
                    .then(function(user){
                        $scope.report({
                            message: 'Login accomplished!',
                            timeout: 2,
                            url: '/'
                        });
                    })
                    .catch(function(ec) {
                        $scope.report({
                            message: 'Errant in either email or password:' + ec.msg,
                            timeout: 2
                        });
                    });
            };
        }
    ]);
})(angular.module('miao.controller'));