(function(module){
    'use strict';

    module.filter('avatar', function() {
        return function(user) {
            if (!user) {
                return null;
            }

            var domain = 'http://7xqdwr.com1.z0.glb.clouddn.com/{id}-avatar?{tag}';

            return domain
                .replace('{id}', user.id)
                .replace('{tag}', user.lastAccess);
        };
    });
})(angular.module('miao.filter'));
