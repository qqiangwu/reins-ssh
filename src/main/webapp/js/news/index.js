$(document).ready(function (e) {
////背景图片
    function sizebgimg() {
        var widwidth = $(window).width();
        var widheight = $(window).height();

    }

    sizebgimg()
    $(window).resize(function () {
        sizebgimg()
    });

    $("#context .c_lt .clt_f h1 .colsen").click(function () {
        $(".clt_f").hide(500);
        $(".openn").show(500);
    })
    $(".openn").click(function () {
        $(".clt_f").show(500);
        $(".openn").hide(500);
    })


    $("#header .hr_l>.menu>ul>li").mousemove(function () {
        var aNum = $(this).find(".mu2 a").length;
        $(this).find(".mu2").css("width", aNum * 75 + "px").show();
        $(this).siblings().find(".mu2").hide()

    })

    $("#header .hr_l>.menu>ul>li").mouseleave(function () {
        $(this).find(".mu2").slideUp();
    })
    
    
    $(".anniu span.hotornew").click(function () {
        if (parseInt($(this).find("font").css("left")) == 0) {
            $(this).find("font").stop().animate({"left": "22px"}, 500);
            $(this).find("img").attr("src", N2CJ.urlResource() + "/images/redof.png");
            sort_left();
        } else {
            $(this).find("font").stop().animate({"left": "0"}, 500);
            $(this).find("img").attr("src", N2CJ.urlResource() + "/images/redd.png");
            sort_right();
        }
    })

    $(window).scroll(function () {
        if ($(window).scrollTop() > 167) {
            $("#header").addClass("mfix");

        } else {
            $("#header").removeClass("mfix");

        }

        if ($(window).scrollTop() > 480) {
            $(".clt_f").addClass("clfix");
        } else {
            $(".clt_f").removeClass("clfix");
        }

    })


    var syi = 0;
    var j = $(".sluli").length;
    $(".slul").css("width", (j * (666 + 2)) * 2 + "px");
    $(".deboe2").html($(".deboe1").html());

    $("#slide .slideimg .prev .pr").click(function () {
        syi--;
        if (syi < 0) {
            $(".slul").css({"left": -j * 668 + "px"});
            syi = j - 1
            $(".slul").animate({"left": -syi * 668 + "px"}, 500);

            imgslide(syi)
        } else {
            $(".slul").animate({"left": -syi * 668 + "px"}, 500);
            imgslide(syi)
        }
    })

    $("#slide .slideimg .next .pr").click(function () {
        syi++;

        if (syi >= j) {

            $(".slul").stop().animate({"left": -syi * 668 + "px"}, 500, function () {
                $(".slul").css({"left": "0"});
            });
            syi = 0;
            imgslide(syi)
        } else {
            $(".slul").animate({"left": -syi * 668 + "px"}, 500);
            imgslide(syi)
        }


    })


    $("#slide .slideimg .slibtn span").click(function () {
        syi = $("#slide .slideimg .slibtn span").index(this);
        $(".slul").animate({"left": -syi * 668 + "px"}, 500);
        imgslide(syi)

    })

///////结束
});


function imgslide(index) {

    $("#slide .slideimg .slibtn span").eq(index).addClass("active").siblings().removeClass("active");
}


(function ($) {
    $.fn.fullBg = function () {
        var bgImg = $(this);
        bgImg.addClass('fullBg');
        function resizeImg() {
            var imgwidth = bgImg.width();
            var imgheight = bgImg.height();
            var winwidth = $(window).width();
            var winheight = $(window).height();
            var widthratio = winwidth / imgwidth;
            var heightratio = winheight / imgheight;
            var widthdiff = heightratio * imgwidth;
            var heightdiff = widthratio * imgheight;
            if (heightdiff > winheight) {
                bgImg.css({
                    width: winwidth + 'px',
                    height: heightdiff + 'px'
                });
            } else {
                bgImg.css({
                    width: widthdiff + 'px',
                    height: winheight + 'px'
                });
            }
        }

        resizeImg();
        $(window).resize(function () {
            resizeImg();
        });
    };
})(jQuery)