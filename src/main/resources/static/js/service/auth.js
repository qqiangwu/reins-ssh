(function(module){
    'use strict';

    module.factory('Auth', ['$http', '$q', '$rootScope',
        function($http, $q, $root){
            function register(userInfo) {
                var deferred = $q.defer();

                $http.post('/api/users', userInfo)
                    .then(function(result){
                        $root.$broadcast('auth:login', result);
                        deferred.resolve(result);
                    }, function(ec){
                        deferred.reject(ec);
                    });

                return deferred.promise;
            }

            function login(email, password) {
                var headers = {
                    authorization: 'Basic ' + email + ':' + password
                };
                var deferred = $q.defer();

                $http.get('/api/users', {
                    email: email,
                    password: password
                }, {
                    headers: headers
                }).then(function(result){
                    $root.$broadcast('auth:login', result);
                    deferred.resolve(result);
                }, function(ec){
                    deferred.reject(ec);
                });

                return deferred.promise;
            }

            function logout() {
                var deferred = $q.defer();

                $http.post('/logout')
                    .then(function(){
                        $root.$broadcast('auth:logout');
                        deferred.resolve();
                    });

                return deferred.promise;
            }

            return {
                register: register,
                login: login,
                logout: logout
            };
        }
    ]);
})(angular.module('miao.service'));