fis.unhook('components');

fis.set('static', 'static');
fis.set('project.files', ['map.json', 'views/**', 'modules/**']);

// ID def
// bower依赖必须全部限定
fis.match('/import/(**)', {
    id: '$1'
});

fis.match('/modules/(**).{js,css}', {
    id: '$1'
});

fis.match(/^\/modules\/([^\/]+)\/\1\.(js|css)$/i, {
    id: '$1'
});

// Output Directory
fis.match('/modules/(**)', {
    release: '${static}/$1'
});

fis.match('/views/(**)', {
    release: '$1'
});

// 因为是纯前段项目，依赖不能自断被加载进来，所以这里需要借助一个 loader 来完成，
// 注意：与后端结合的项目不需要此插件!!!
fis.match('::package', {
    // npm install [-g] fis3-postpackager-loader
    // 分析 __RESOURCE_MAP__ 结构，来解决资源加载问题
    postpackager: fis.plugin('loader')
});
