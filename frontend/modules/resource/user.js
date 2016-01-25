// @require angular
// @require angular-resource

(function(module){
    'use strict';

    module.factory('User', ['$resource', function($resource){

        return $resource('api/users/:id', {id: '@id'}, {
            update: {
                method: 'POST'
            }
        });
    }]);
})(angular.module('resource.user', ['ngResource']));
