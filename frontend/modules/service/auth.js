// @require /import/angular/angular.js
// @require service/conf

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

            function setState(user) {
                $conf.set(key, true);
                _user = user;
            }

            function invalidate() {
                $conf.clear(key);
                _user = null;
            }

            // log info persistence
            (function init(){
                if ($conf.get(key)) {
                    $http.get("api/users")
                        .then(function(result){
                            setState(result.data);
                        })
                        .catch(invalidate);
                }

                $root.$on('auth:login', function(_, user){
                    setState(user);
                });

                $root.$on('auth:logout', invalidate);

                $root.$on('monitor:unauthorized', invalidate);

                $root.$on('comment:add', function(){
                    if (_user) {
                        ++_user.commentCount;
                    }
                });

                $root.$on('blog:add', function(){
                    if (_user) {
                        ++_user.blogCount;
                    }
                });

                $root.$on('blog:delete', function(){
                    if (_user) {
                        --_user.blogCount;
                    }
                });
            })();

            return {
                register: register,
                login: login,
                logout: logout,
                invalidate: invalidate,
                user: function() {
                    return _user;
                },
                hasLogin: function() {
                    return !!_user;
                },
                update: function(user) {
                    _user = user;
                }
            };
        }
    ]);
})(angular.module('service.auth', ['service.conf']));
