/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogEditCtrl', ['$scope', '$routeParams', 'Blog', 'Category',
        function($scope, $routeParams, Blog, Category){
            Blog.get({id: $routeParams.id})
                .$promise
                .then(function(res){
                    $scope.blog = res;
                    $scope.categories = Category.query(function(){
                        $scope.blog.category = _.find($scope.categories, $scope.blog.category? function(elem){ 
                            return elem.id === $scope.blog.category.id;
                        }: function(elem){
                            return elem.name === 'Default';
                        });
                    });
                });

            $scope.update = function(){
                $scope.blog.$update(
                    function(){
                        $scope.report({
                            message: 'Modify blog success',
                            timeout: 2,
                            url: '/blogView/' + $routeParams.id
                        });
                    },
                    function(){
                        $scope.report({
                            message: 'Modify blog wrong. Please check your network',
                            timeout: 2
                        });
                    }
                );
            };
        }
    ]);
})(angular.module('miao.controller'));