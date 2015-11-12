/**
 * Created by qqiangwu on 11/12/15.
 */
(function($){
    'use strict';

    $('form').submit(function(e){
        var title = $('#note-title');
        var content = $('#note-content');

        if (!title.val() || !content.val()) {
            alert('Title or content is empty')
        } else {
            $.post('/api/note/add', {
                title: title.val(),
                content: content.val()
            }, function(){
                $('<li>' + title.val() + '----' + content.val() + '</li>').appendTo($('ul'));

                title.val('');
                content.val('');
            });
        }

        e.preventDefault();
    });
})(jQuery);