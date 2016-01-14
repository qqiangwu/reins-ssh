(function(module){
    'use strict';

    module.factory('Auth', ['$http', '$q', '$rootScope', 'Conf',
        function($http, $q, $root, $conf){
            var _user = null;

            function register(userInfo) {
                var deferred = $q.defer();

                $http.post('/api/users', userInfo)
                    .then(function(resp){
                        $root.$broadcast('auth:login', resp.data);
                        deferred.resolve(resp.data);
                    }, function(resp){
                        deferred.reject(resp.data, resp.status);
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
                    $root.$broadcast('auth:login', result.data);
                    deferred.resolve(result.data);
                }, function(resp){
                    deferred.reject(resp.data, resp.status);
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

            // log info persistence
            (function init(){
                var key = 'auth_user';

                _user = $conf.get(key);

                $root.$on('auth:login', function(ev, user){
                    $conf.set(key, user);
                });

                $root.$on('auth:logout', function(){
                    $conf.clear(key);
                });
            })();

            return {
                register: register,
                login: login,
                logout: logout,
                user: function() {
                    return _user;
                }
            };
        }
    ]);
})(angular.module('miao.service'));