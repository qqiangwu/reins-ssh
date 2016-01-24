// @require /import/angular/angular.js

(function(module){
    'use strict';

    module.factory('Monitor', function($rootScope, $location, $log, $q){
        "ngInject";

        var monitorApi = {};

        Object.defineProperties(monitorApi, {
            report: {
                value: function(message){
                    alert(message);
                },
                writable: false,
                enumerable: false,
                configurable: false
            },
            responseFilter: {
                value: function(resp){
                    if (resp.status === 401) {
                        $root.$broadcast('monitor:unauthorized');
                    }
                    if (resp.status === 500) {
                        $log.error('[Monitor] Internal server error:', resp);

                        this.report('Internal server error detected, see the log');
                    }

                    return $q.reject(resp);
                },
                configurable: false,
                writable: false,
                enumerable: false
            }
        });

        Object.freeze(monitorApi);

        return monitorApi;
    });

    module.config(['$httpProvider', function($httpProvider) {
            $httpProvider.interceptors.push({
                responseError: Monitor.responseFilter.bind(Monitor)
            });
        }
    ]);
})(angular.module('service.monitor', []));
