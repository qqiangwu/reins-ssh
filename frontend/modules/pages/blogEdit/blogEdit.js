(function(module){
    'use strict';

    module.controller('BlogEditCtrl', function($scope, $routeParams, Blog){
        "ngInject";

        if (!$scope.hasLogin()) {
            $scope.go();
        }

        Blog.get({id: $routeParams.id})
            .$promise
            .then(function(blog){
                $scope.blog = blog;

                if ($scope.blog.user.id !== $scope.user().id) {
                    $scope.go();
                }
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
                function(resp){
                    var ec = resp.data;
                    var status = resp.status;

                    switch (status) {
                        case 400:
                            $scope.report({
                                message: 'Either title or content is invalid:'+ec.message,
                                timeout: 2
                            });
                            break;

                        default:
                            $scope.report({
                                message: 'Modify blog wrong. Please check your network',
                                timeout: 2
                            });
                            break;
                    }
                }
            );
        };
    });

    return {
        url: '/blogEdit/:id',
        config: {
            template: __inline('./blogEdit.html'),
            controller: 'BlogEditCtrl'
        }
    };
})(angular.module('pages'));
