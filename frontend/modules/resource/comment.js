// @require /import/angular/angular.js
// @require /import/angular-resource/angular-resource.js

(function(module){
    'use strict';

    module.factory('Comment', function($resource){
        "ngInject";

        return $resource('api/blogs/:id/comments', {id: '@id'}, {
            query: {
                isArray: false,
                method: 'GET'
            }
        });
    });
})(angular.module('resource.comment', ['ngResource']));
