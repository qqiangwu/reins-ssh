(function(module){
    'use strict';

    module.constant('InternalServerError', 500);

    module.factory('Monitor', ['$rootScope', '$location', '$log', 'InternalServerError', '$q',
        function($root, $location, $log, InternalServerError, $q){
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
                        if (resp.status == 401) {
                            $root.$broadcast('monitor:unauthorized');
                        }
                        if (resp.status === InternalServerError) {
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
        }
    ]);

    module.factory('Monitor.responseFilter', ['Monitor',
        function(Monitor){
            return {
                responseError: Monitor.responseFilter.bind(Monitor)
            };
        }
    ]);
})(angular.module('miao.service'));