// @require /import/angular/angular.js
// @require /import/angular-resource/angular-resource.js

(function(module){
    'use strict';

    module.factory('Comment', ['$resource', function($resource){

        return $resource('api/blogs/:id/comments', {id: '@id'}, {
            query: {
                isArray: false,
                method: 'GET'
            }
        });
    }]);
})(angular.module('resource.comment', ['ngResource']));
