(function(module){
    'use strict';

    module.controller('MainCtrl', ['$scope', 'Blog', function($scope, Blog){
        $scope.pageSize = 10;
        $scope.currentPage = 1;

        $scope.loadPage = function(){
            $scope.blogs = Blog.query({
                page: $scope.currentPage - 1,
                size: $scope.pageSize
            }, function(value){
                $scope.blogs = value.content;
                $scope.totalElements = value.totalElements;
                $scope.currentPage = value.number + 1;
            });
        };

        $scope.loadPage();

        Blog.query({
            page: 0,
            size: 5
        }, function(val){
            $scope.newBlogs = val.content;
        });
        Blog.query({
            page: 0,
            size: 5,
            order: "hot"
        }, function(val){
            $scope.hotBlogs = val.content;
        });
    }]);

    return {
        url: '/main',
        config: {
            template: __inline('./main.html'),
            controller: 'MainCtrl'
        }
    };
})(angular.module('pages'));
