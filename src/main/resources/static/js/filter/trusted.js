/**
 * Created by Float on 14-9-7.
 */
(function(module){
    module.filter('trusted',['$sce',
        function($sce){
            return function(html){
                return $sce.trustAsHtml(html);
            };
        }
    ]);
})(angular.module('miao.filter'));