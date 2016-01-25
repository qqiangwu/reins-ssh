// @require angular
// @require angular-route

// @require resource/blog
// @require resource/comment
// @require resource/user
// @require service/auth

(function(module){
    var blogCreate = __inline('./blogCreate/blogCreate.js');
    var blogEdit = __inline('./blogEdit/blogEdit.js');
    var blogView = __inline('./blogView/blogView.js');
    var dashboard = __inline('./dashboard/dashboard.js');
    var login = __inline('./login/login.js');
    var main = __inline('./main/main.js');
    var register = __inline('./register/register.js');
    var self = __inline('./self/self.js');

    module.constant('PageDef', [
        blogCreate,
        blogEdit,
        blogView,
        dashboard,
        login,
        main,
        register,
        self
    ]);
})(angular.module('pages', [
    'ngRoute',
    'resource.blog',
    'resource.comment',
    'resource.user',
    'service.auth'
]));
