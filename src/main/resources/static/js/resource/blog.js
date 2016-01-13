
(function(module){
    'use strict';

    module.factory('Blog', ['$resource',
        function($resource){
            return $resource('api/blog/:id', {id: '@id'}, {
                update: {
                    method: 'PUT'
                },
                meta: {
                    url: 'api/blog',
                    method: 'GET'
                },
                query: {
                    isArray: true,
                    url: 'api/blog/:from/:to',
                    method: 'GET'
                }
            });
        }
    ]);
})(angular.module('miao.resource'));