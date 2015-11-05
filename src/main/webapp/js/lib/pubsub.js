/*
 *
 * A very simple pubsub system built on top of jquery.
 */

(function ($) {
    'use strict';

    var o = $({});

    $.sub = function () {
        o.on.apply(o, arguments);
    };

    $.unsub = function () {
        o.off.apply(o, arguments);
    };

    $.pub = function () {
        o.trigger.apply(o, arguments);
    };
}(jQuery));