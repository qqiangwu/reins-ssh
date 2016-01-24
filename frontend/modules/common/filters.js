(function(){
    'use strict';

    // @require /import/angular/angular.js

    var common = angular.module('common.filters', []);

    common.filter('avatar', function() {
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

    common.filter('timeago', function() {
        return function (input, p_allowFuture) {
            var substitute = function (stringOrFunction, number, strings) {
                    var string = angular.isFunction(stringOrFunction) ? stringOrFunction(number, dateDifference) : stringOrFunction;
                    var value = (strings.numbers && strings.numbers[number]) || number;
                    return string.replace(/%d/i, value);
                },
                nowTime = (new Date()).getTime(),
                date = (new Date(input)).getTime(),
                allowFuture = p_allowFuture || false,
                strings = {
                    prefixAgo: '',
                    prefixFromNow: '',
                    suffixAgo: "前",
                    suffixFromNow: "后",
                    seconds: "少于一分钟",
                    minute: "一分钟",
                    minutes: "%d分钟",
                    hour: "一小时",
                    hours: "%d小时",
                    day: "一天",
                    days: "%d天",
                    month: "一个月",
                    months: "%d月",
                    year: "一年",
                    years: "%d年"
                },
                dateDifference = nowTime - date,
                words,
                seconds = Math.abs(dateDifference) / 1000,
                minutes = seconds / 60,
                hours = minutes / 60,
                days = hours / 24,
                years = days / 365,
                separator = strings.wordSeparator === undefined ? " " : strings.wordSeparator,


                prefix = strings.prefixAgo,
                suffix = strings.suffixAgo;

            if (allowFuture) {
                if (dateDifference < 0) {
                    prefix = strings.prefixFromNow;
                    suffix = strings.suffixFromNow;
                }
            }

            words = seconds < 45 && substitute(strings.seconds, Math.round(seconds), strings) ||
                seconds < 90 && substitute(strings.minute, 1, strings) ||
                minutes < 45 && substitute(strings.minutes, Math.round(minutes), strings) ||
                minutes < 90 && substitute(strings.hour, 1, strings) ||
                hours < 24 && substitute(strings.hours, Math.round(hours), strings) ||
                hours < 42 && substitute(strings.day, 1, strings) ||
                days < 30 && substitute(strings.days, Math.round(days), strings) ||
                days < 45 && substitute(strings.month, 1, strings) ||
                days < 365 && substitute(strings.months, Math.round(days / 30), strings) ||
                years < 1.5 && substitute(strings.year, 1, strings) ||
                substitute(strings.years, Math.round(years), strings);

            prefix.replace(/ /g, '');
            words.replace(/ /g, '');
            suffix.replace(/ /g, '');

            return (prefix + ' ' + words + ' ' + suffix + ' ' + separator);
        };
    });

    common.directive('editor', [
        function(){
            return {
                restrict: 'A',
                link: function($scope, $elem, $attr){
                    $elem.bind('keydown', function(e){
                        if (e.keyCode === 9) {
                            var val = this.value;
                            var start = this.selectionStart;
                            var end = this.selectionEnd;

                            this.value = val.substring(0, start) + '\t' + val.substring(end);
                            this.selectionStart = this.selectionEnd = start + 1;

                            e.preventDefault();
                        }
                    });
                }
            };
        }
    ]);
})();
