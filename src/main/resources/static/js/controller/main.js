(function(module){
    'use strict';

    module.controller('MainCtrl', ['$scope', 'Blog',
        function($scope, Blog){
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
        }
    ]);
})(angular.module('miao.controller'));