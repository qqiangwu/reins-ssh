(function(module){
    'use strict';

    module.factory('Auth', ['$http', '$q', '$rootScope', 'Conf',
        function($http, $q, $root, $conf){
            var key = 'auth_user';
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
                var deferred = $q.defer();

                $http.post('/login', {
                    email: email,
                    password: password
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

            function invalidate() {
                $conf.clear(key);
                _user = null;
            }

            // log info persistence
            (function init(){
                _user = $conf.get(key);

                $root.$on('auth:login', function(ev, user){
                    $conf.set(key, user);
                    _user = user;
                });

                $root.$on('auth:logout', function(){
                    invalidate();
                });

                $root.$on('monitor:unauthorized', function(){
                    invalidate();
                    $scope.go();
                });
            })();

            return {
                register: register,
                login: login,
                logout: logout,
                invalidate: invalidate,
                user: function() {
                    return _user;
                }
            };
        }
    ]);
})(angular.module('miao.service'));