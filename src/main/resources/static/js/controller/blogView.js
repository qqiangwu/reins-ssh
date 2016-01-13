/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogViewCtrl', ['$scope', '$routeParams', 'Blog',
        function($scope, $routeParams, Blog){
            $scope.blog = Blog.get({id: $routeParams.id});

            $scope.remove = function(){
                $scope.blog.$remove(function(){
                    $scope.log.info('[Blog | remove] ', $scope.blog.id);
                    $scope.go('/blogList');
                });
            };
        }
    ]);
})(angular.module('miao.controller'));