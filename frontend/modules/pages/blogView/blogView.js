(function(module){
    'use strict';

    module.controller('BlogViewCtrl', ['$scope', '$routeParams', 'Blog', 'Comment',
        function($scope, $routeParams, Blog, Comment){

        var id = $routeParams.id;

        $scope.blog = Blog.get({id: $routeParams.id}, function(){
            $scope.loadPage();
        });

        Blog.addView({
            id: id
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

        if ($scope.hasLogin()) {
            $scope.postComment = function(content){
                new Comment({
                    id: $scope.blog.id,
                    content: content
                }).$save(function(c){
                    $scope.report({
                        message: 'Post comment successfully',
                        timeout: 1
                    });

                    $scope.comments.unshift(c);
                    $scope.$emit('comment:add');

                    ++$scope.blog.commentCount;
                });
            };
        }
    }]);

    return {
        url: '/blogView/:id',
        config: {
            template: __inline('./blogView.html'),
            controller: 'BlogViewCtrl'
        }
    };
})(angular.module('pages'));
