/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogViewCtrl', ['$scope', '$routeParams', 'Blog',
        function($scope, $routeParams, Blog){
            $scope.blog = Blog.get({id: $routeParams.id});
        }
    ]);
})(angular.module('miao.controller'));