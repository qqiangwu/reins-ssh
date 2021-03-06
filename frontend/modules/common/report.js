(function(){
    'use strict';

    // @require angular
    // @require /import/bootstrap/dist/css/bootstrap.css
    // @require angular-bootstrap

    var common = angular.module('common.report', ['ui.bootstrap']);

    common.controller('ModalInstanceCtrl', ['$scope', '$timeout', '$modalInstance', 'option',
        function($scope, $timeout, $modalInstance, option){
        // adjust options
        $scope.message = option.message;
        $scope.title = option.title || 'Message';

        $scope.ok = function(){ $modalInstance.close(); };

        //! setup timeout
        if (angular.isNumber(option.timeout) && option.timeout > 0) {
            $timeout(function callback(){
                if ($scope.timeout > 0) {
                    $scope.timeout -= 1;
                    $timeout(callback, 1000);
                }
                else {
                    $scope.$emit('timeout');
                }
            }, 1000);

            $scope.timeout = option.timeout;
            $scope.$on('timeout', function(event){
                $modalInstance.close();
            });
        }
    }]);

    common.factory('CommonReport', ['$rootScope', '$modal', function($rootScope, $modal){
        return function(option){
            var result = $modal.open({
                template: __inline('modal.html'),
                controller: 'ModalInstanceCtrl',
                size: 'sm',
                resolve: {
                    option: function(){
                        return option;
                    }
                }
            }).result;

            if (option.url) {
                result.finally(function(){
                    $rootScope.$broadcast('modal:dismiss', option);
                });
            }
        };
    }]);
})();
