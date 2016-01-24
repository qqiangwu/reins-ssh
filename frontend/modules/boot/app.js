// @require /import/angular/angular.js
// @require /import/angular-sanitize/angular-sanitize.js
// @require /import/showdown/src/showdown.js
// @require /import/angular-markdown-directive/markdown.js
// @require /import/bootstrap/dist/css/bootstrap.css
// @require /import/angular-bootstrap/ui-bootstrap-tpls.js

// @require boot/router
// @require service/auth
// @require common/filters
// @require common/report

(function(){
    'use strict';

    var app = angular.module('miao', [
        'ui.bootstrap',
        'btford.markdown',
        'flow',
        'common.filters',
        'common.report',
        'boot.router',
        'service.auth'
    ]);

    // 设置post格式兼容spring rest
    app.config(function($httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.transformRequest = function(req) {
            var str = [];
            for(var p in req) {
                if (p[0] != '$') {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(req[p]));
                }
            }
            return str.join("&");
        };
    });

    app.run(function($rootScope, $location, CommonReport){
        $rootScope.go = function(url, reload){
            $location.path(url || '/');

            if (reload) {
                $location.reload(true);
            }
        };

        $rootScope.report = CommonReport.report;
        $rootScope.$on('modal:dismiss', function(_, options){
            $rootScope.go(options.url, options.reload);
        });
    });

    app.run(function($rootScope, Auth){
        var $root = $rootScope;

        $root.hasLogin = Auth.hasLogin;
        $root.user = Auth.user;

        $root.$on('monitor:unauthorized', function(){
            $root.go();
        });

        $root.logout = function() {
            Auth.logout().then(function(){
                $root.report({
                    message: 'Logout successfully',
                    timeout: 2,
                    url: '/'
                });
            });
        };
    });
})();
