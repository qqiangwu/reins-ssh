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
                if ($conf.get(key)) {
                    $http.get("api/users")
                        .then(function(result){
                            _user = result;
                        })
                        .catch(function(){
                            invalidate();
                        });
                }

                $root.$on('auth:login', function(ev, user){
                    $conf.set(key, true);
                    _user = user;
                });

                $root.$on('auth:logout', function(){
                    invalidate();
                });

                $root.$on('monitor:unauthorized', function(){
                    invalidate();
                });

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
                })
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
                }
            };
        }
    ]);
})(angular.module('miao.service'));