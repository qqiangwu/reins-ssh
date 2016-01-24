// @require /import/angular/angular.js
// @require /import/angular-resource/angular-resource.js

(function(module){
    'use strict';

    module.factory('User', function($resource){
        return $resource('api/users/:id', {id: '@id'}, {
            update: {
                method: 'POST'
            }
        });
    });
})(angular.module('resource.user'));
