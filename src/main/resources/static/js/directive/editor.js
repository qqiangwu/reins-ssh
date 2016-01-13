/**
 * Created by Float on 14-9-9.
 */
(function(module){
    'use strict';

    module.directive('editor', [
        function(){
            return {
                restrict: 'A',
                link: function($scope, $elem, $attr){
                    $elem.bind('keydown', function(e){
                        if (e.keyCode === 9) {
                            var val = this.value;
                            var start = this.selectionStart;
                            var end = this.selectionEnd;

                            this.value = val.substring(0, start) + '\t' + val.substring(end);
                            this.selectionStart = this.selectionEnd = start + 1;

                            e.preventDefault();
                        }
                    });
                }
            };
        }
    ]);
})(angular.module('miao.directive'));
