/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogListCtrl', ['$scope', 'Blog', 'Category',
        function($scope, Blog, Category){
            $scope.currentPage = 1;
            $scope.blogs = [];
            $scope.categories = Category.query();


            $scope.loadPage = function(){
                var to = $scope.currentPage * $scope.itemsPerPage;
                var from = to - $scope.itemsPerPage;

                $scope.blogs = Blog.query({from: from, to: to});

                $scope.log.info('load items from ', from, ' to ', to);
            };

            $scope.remove = function(blog){
                var id = blog.id;
                blog.$remove(function(){
                    $scope.log.info('remove blog ', id);
                    $scope.blogs = _($scope.blogs).without(blog);

                    --$scope.count;
                });
            };

            $scope.changeCategory = function(cate){
                $scope.log.debug('[BlogList] change to', cate);
            };

            /* init */
            Blog.meta(function(res){
                $scope.log.info('[Blog:', res.count, ']');

                $scope.count = res.count;

                $scope.loadPage();
            });
        }
    ]);
})(angular.module('miao.controller'));