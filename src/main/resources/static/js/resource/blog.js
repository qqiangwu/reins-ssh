
(function(module){
    'use strict';

    module.factory('Blog', ['$resource',
        function($resource){
            return $resource('api/blogs/:id', {id: '@id'}, {
                update: {
                    method: 'PUT'
                },
                save: {
                    method: 'POST',
                    url: 'api/blogs'
                },
                query: {
                    isArray: false,
                    method: 'GET'
                }
            });
        }
    ]);
})(angular.module('miao.resource'));