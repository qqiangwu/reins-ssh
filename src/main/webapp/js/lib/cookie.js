var ECookie = (function () {
    'use strict';

    function get(key, defaultValue) {
        var cookie = document.cookie;
        var regex = new RegExp(key + '=([\\w\\d]+)');
        var result = regex.exec(cookie);

        return result && result.length > 1 ? result[1] === '1' : defaultValue;
    }

    function set(key, value) {
        document.cookie = key + '=' + Number(value) + '; path=/';
    }

    function setText(key, value) {
        document.cookie = key + '=' + String(value) + '; path=/';
    }

    function getText(key, defaultValue) {
        var cookie = document.cookie;
        var regex = new RegExp(key + '=([X\\d]+)');
        var result = regex.exec(cookie);

        return result && result.length > 1 ? result[1] : defaultValue;
    }

    return {
        get: get,
        set: set,
        setText: setText,
        getText: getText
    };
})();