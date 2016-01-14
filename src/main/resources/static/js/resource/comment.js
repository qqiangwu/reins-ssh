
(function(module){
    'use strict';

    module.factory('Comment', ['$resource',
        function($resource){
            return $resource('api/blogs/:id/comments', {id: '@id'}, {
                query: {
                    isArray: false,
                    method: 'GET'
                }
            });
        }
    ]);
})(angular.module('miao.resource'));