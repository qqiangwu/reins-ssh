(function(module){
    module.constant('PageDef', [
        __inline('./blogCreate/blogCreate.js'),
        __inline('./blogEdit/blogEdit.js'),
        __inline('./blogView/blogView.js'),
        __inline('./dashboard/dashboard.js'),
        __inline('./login/login.js'),
        __inline('./main/main.js'),
        __inline('./register/register.js'),
        __inline('./self/self.js')
    ]);
})(angular.module('pages', []));
