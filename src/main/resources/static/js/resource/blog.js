
(function(module){
    'use strict';

    module.factory('Blog', ['$resource',
        function($resource){
            return $resource('api/blogs/:id', {id: '@id'}, {
                update: {
                    method: 'POST'
                },
                save: {
                    method: 'POST',
                    url: 'api/blogs'
                },
                query: {
                    isArray: false,
                    method: 'GET'
                },
                queryByUser: {
                    url: 'api/users/:user/blogs',
                    method: 'GET',
                    isArray: false
                }
            });
        }
    ]);
})(angular.module('miao.resource'));