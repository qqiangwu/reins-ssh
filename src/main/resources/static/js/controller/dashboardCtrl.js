(function(module){
    'use strict';

    module.controller('DashboardCtrl', ['$scope', 'Blog', 'Comment',
        function($scope, Blog, Comment){
            if (!$scope.user()) {
                $scope.go();
            }

            $scope.pageSize = 10;
            $scope.currentPage = 1;

            $scope.loadPage = function(){
                $scope.blogs = Blog.queryByUser({
                    user: $scope.user().id,
                    page: $scope.currentPage - 1,
                    size: $scope.pageSize
                }, function(value){
                    $scope.blogs = value.content;
                    $scope.totalElements = value.totalElements;
                    $scope.currentPage = value.number + 1;
                });
            };
            $scope.remove = function(idx, blog){
                Blog.delete({
                    id: blog.id
                }, function(){
                    $scope.report({
                        message: 'Delete blog successfully',
                        timeout: 1
                    });
                    $scope.blogs.splice(idx, 1);
                });
            };

            $scope.loadPage();
        }
    ]);
})(angular.module('miao.controller'));