/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('BlogCreateCtrl', ['$scope', 'Blog', 'Category',
        function($scope, Blog, Category){
            Category.query()
                    .$promise
                    .then(function(res){
                        var category = _.find(res, function(elem){ return elem.name === 'Default'; });
                        $scope.categories = res;
                        $scope.blog = new Blog({category: category});
                    });

            $scope.save = function(){
                $scope.blog.$save(
                    function(blog){
                        $scope.report({
                            message: 'Post blog success',
                            timeout: 2,
                            url: '/blogView/' + blog.id
                        });
                    },
                    function(){
                        $scope.report({
                            message: 'Post blog wrong. Please check your network',
                            timeout: 2
                        });
                    }
                );
            };
        }
    ]);
})(angular.module('miao.controller'));