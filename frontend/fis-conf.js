fis.unhook('components');

fis.set('static', 'static');
fis.set('project.files', ['map.json', 'views/**', 'modules/**']);

/////////////////////////////////////////////////////////
// ID def
// bower依赖必须全部限定
fis.match('/import/(**)', {
        id: '$1'
    })
    .match('/modules/(**).{js,css}', {
        id: '$1'
    })
    .match(/^\/modules\/([^\/]+)\/\1\.(js|css)$/i, {
        id: '$1'
    });

////////////////////////////////////////////////////////
// Output Directory
fis.match('/modules/(**)', {
        release: '${static}/$1'
    })
    .match('/views/(**)', {
        release: '$1'
    })
    .match('partials/**', {
        release: false
    });

///////////////////////////////////////////////////////
// 因为是纯前段项目，依赖不能自断被加载进来，所以这里需要借助一个 loader 来完成，
fis.match('::package', {
    postpackager: [
        fis.plugin('loader'),
        fis.plugin('inline')
    ]
});

//////////////////////////////////////////////////////
fis.media('prod')
    .set('static', 's')
    .set('pkg', 'p')
    .match('::package', {
        packager: fis.plugin('map', {
            useTrack: false
        })
    })
    .match('/map.json', {
        release: false
    })
    .match('**.js', {
        preprocessor: fis.plugin('annotate'),
//        optimizer: fis.plugin('uglify-js'),
        useHash: true
    })
    .match('**.css', {
        optimizer: fis.plugin('clean-css'),
        useHash: true
    })
    .match('index.html', {
        optimizer: fis.plugin('html-minifier')
    })
    .match('/import/**.js', {
        packTo: '${pkg}/vendor.js'
    })
    .match('/import/**.css', {
        packTo: '${pkg}/vendor.css'
    })
    .match('/modules/**.js', {
        packTo: '${pkg}/core.js'
    })
    .match('views/**.css', {
        release: '${static}/$&',
        packTo: '${static}/core.inline.css'
    })
    .match('fonts/(**)', {
        release: '${pkg}/$1'
    });
