(function(module){
    'use strict';

    module.controller('BlogCreateCtrl', function($scope, Blog){
        "ngInject";

        if (!$scope.hasLogin()) {
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
                    $scope.$emit('blog:add');
                },
                function(resp){
                    var status = resp.status;

                    if (status === 400) {
                        $scope.report({
                            message: 'Invalid title or content',
                            timeout: 2
                        });
                    }
                }
            );
        };
    });

    return {
        url: '/blogCreate',
        config: {
            template: __inline('./blogCreate.html'),
            controller: 'BlogCreateCtrl'
        }
    };
})(angular.module('pages'));
