function newsDetail() {
    var ids = '';
    var count = 0;
    $("#newslist").children("div.nlist").each(function (i) {
    	if(typeof($(this).attr('id'))=="undefined"){
    		
    	}else{
    		if (count != 0) {
                ids += ",";
            }
            ids += $(this).attr('id').substring(5);
            count = count + 1;
    	}
    });

    $.ajax({
        url: '/api/news/newsdetail',
        type: 'GET',
        data: {"ids": ids},
        dataType: 'json',
        success: function (data) {
            $.each(data, function (index) {
                $("#newslist").children("#news_" + data[index].newsId).children("div.nnr").children("div.snstime").children("span.look").text(data[index].newsView);
                $("#newslist").children("#news_" + data[index].newsId).children("div.nnr").children("div.snstime").children("span.msg").text(data[index].newsComment);
                $("#newslist").children("#news_" + data[index].newsId).children("div.nnr").children("div.snstime").children("span.good").text(data[index].newsLike);
            });
        },
        error: function (data) {
            return false;
        }
    });

}

function sort_left() {
    $("div.nlist").sortElements(function (a, b) {
        return Number($(a).children("div.nnr").children("div.snstime").children("span.look").text()) > Number($(b).children("div.nnr").children("div.snstime").children("span.look").text()) ? -1 : 1;
    });
}

function sort_right() {
    $("div.nlist").sortElements(function (a, b) {
        return $(a).children("div.nnr").children("div.snstime").children("span.time").text() > $(b).children("div.nnr").children("div.snstime").children("span.time").text() ? -1 : 1;
    });
}

$(function () {
    newsDetail();
}); 