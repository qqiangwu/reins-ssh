(function(module){
    'use strict';

    module.controller('BlogCreateCtrl', ['$scope', 'Blog',
        function($scope, Blog){
            if (!$scope.hasLogin) {
                $scope.go();
            }

            $scope.blog = new Blog();
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