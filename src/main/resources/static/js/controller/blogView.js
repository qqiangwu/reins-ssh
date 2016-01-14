/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogViewCtrl', ['$scope', '$routeParams', 'Blog', 'Comment',
        function($scope, $routeParams, Blog, Comment){
            $scope.blog = Blog.get({id: $routeParams.id}, function(){
                $scope.loadPage();
            });

            $scope.pageSize = 10;
            $scope.currentPage = 1;

            $scope.loadPage = function() {
                Comment.query({
                    id: $scope.blog.id,
                    page: $scope.currentPage - 1,
                    size: $scope.pageSize
                }, function(value){
                    $scope.comments = value.content;
                    $scope.totalElements = value.totalElements;
                    $scope.currentPage = value.number + 1;
                });
            };
            $scope.postComment = function(content){
                var c = new Comment({
                    id: $scope.blog.id,
                    content: content
                }).$save(function(){
                    $scope.report({
                        message: 'Post comment successfully',
                        timeout: 1
                    });
                    $scope.loadPage();
                });
            };
        }
    ]);
})(angular.module('miao.controller'));