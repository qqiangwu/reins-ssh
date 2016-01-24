(function(){
    'use strict';

    // @require /import/angular/angular.js
    // @require /import/bootstrap/dist/css/bootstrap.css
    // @require /import/angular-bootstrap/ui-bootstrap-tpls.js

    var common = angular.module('common.report', ['ui.bootstrap',]);

    common.controller('ModalInstanceCtrl', function($scope, $timeout, $modalInstance, option){
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
    });

    common.factory('CommonReport', function($rootScope, $modal){
        return function(option){
            var result = $modal.open({
                templateUrl: __inline('modal.html'),
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
    });
})();
