/**
 * Created by Float on 14-9-2.
 */
(function(module){
    'use strict';

    module.controller('ModalInstanceCtrl', ['$scope', '$timeout', '$modalInstance', 'option',
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
        }
    ]);
})(angular.module('miao.controller'));