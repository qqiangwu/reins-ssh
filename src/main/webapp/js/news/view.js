/*!
 * 
 *	Js for view.jsp. Managing single news display.
 */

var viewCtrl = (function ($, N2CJ) {
    'use strict';

    console.log('Define module viewCtrl');

    var setFontSize = function (size) {
        var content = $('#news-content');

        switch (size) {
            case 'sm':
                content.css('font-size', '12px');
                break;

            case 'md':
                content.css('font-size', '14px');
                break;

            case 'lg':
                content.css('font-size', '18px');
                break;
        }
    };

    var newsDetail = function (id) {
        var ids = id;

        $.ajax({
            url: '/api/news/newsdetail',
            type: 'GET',
            data: {"ids": ids},
            dataType: 'json',
            success: function (data) {
                $.each(data, function (index) {
                    $("#newscontent").children("div.snstime").children("span.look").text(data[index].newsView);
                    $("#newscontent").children("div.snstime").children("span.msg").text(data[index].newsComment);
                    $("#newscontent").children("div.snstime").children("span.good").text(data[index].newsLike);
                });
            },
            error: function (data) {
                return false;
            }
        });

    }

    var addView = function (newsId) {
        var endpoint = '/api/news/addview';

        $.post(endpoint, {
            newsId: newsId
        });
    };

    return {
        setFontSize: setFontSize,
        addView: addView,
        newsDetail: newsDetail
    };
}($, N2CJ));