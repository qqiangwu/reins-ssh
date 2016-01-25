// @require angular
// @require angular-resource

(function(module){
    'use strict';

    module.factory('Blog', ['$resource', function($resource){
        "ngInject";

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
            },
            addView: {
                method: 'POST',
                url: 'api/blogs/:id/view'
            }
        });
    }]);
})(angular.module('resource.blog', ['ngResource']));
