/**
 * Created by Float on 14-9-6.
 */
(function(module){
    'use strict';

    module.factory('Conf', function(){
        var store = window.localStorage;

        function get(key) {
            var val = store[key];
            return val? JSON.parse(val): null;
        }

        function set(key, val) {
            store[key] = JSON.stringify(val);
        }

        function clear(key) {
            delete store[key];
        }

        return {
            get: get,
            set: set,
            clear: clear
        }
    });
})(angular.module('miao.service'));