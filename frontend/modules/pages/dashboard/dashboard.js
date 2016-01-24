(function(module){
    'use strict';

    module.controller('SelfBlogCtrl', ['$scope', 'Blog', 'Comment',
        function($scope, Blog, Comment){
            if (!($scope.user && $scope.user())) {
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
                    $scope.$emit('blog:delete');
                });
            };

            $scope.loadPage();
        }
    ]);

    module.controller('SelfProfileCtrl', ['$scope', 'Auth', 'User', function($scope, Auth, User){
        // @require import/ng-flow/dist/ng-flow-standalone.min.js

        if (!($scope.user && $scope.user())) {
            $scope.go();
        }

        $scope.upload = function(userName, image) {
            if (!userName) {
                $scope.report({
                    message: "Invalid user name",
                    timeout: 1
                });
                return;
            }

            var fileReader = new FileReader();
            fileReader.readAsDataURL(image.file);
            fileReader.onload = function (event) {
                User.update({
                    id: $scope.user().id,
                    userName: userName,
                    image: event.target.result
                }, function(result) {
                    $scope.report({
                        message: '修改信息成功, 这需要一段时间才能生效',
                        timeout: 1,
                        url: '/',
                        reload: true
                    });

                    Auth.update(result);
                });
            };
        };
    }]);

    module.controller('DashboardCtrl', ['$scope', function($scope){

    }]);
})(angular.module('miao.controller'));
