<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <base target="_blank"/>
    <title>外汇新闻-金汇财经</title>

    <link href="/css/index.css" rel="stylesheet" type="text/css"/>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="/js/lib/respond.min.js"></script>
    <script type="text/javascript" src="/js/lib/html5shiv.min.js"></script>
    <![endif]-->

    <script src="http://dup.baidustatic.com/js/ds.js"></script>

    <link href="/favicon.ico" rel="bookmark" type="image/x-icon"/>
    <link href="/favicon.ico" rel="icon" type="image/x-icon"/>
    <link href="/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="true"/>

<div id="context">
    <div class="c_lt">
        <div class="openn">+</div>
        <div class="clt_f">
            <h2>新闻
                <span class="colsen">X</span>
            </h2>
            <jsp:include page="../common/navbar.jsp"></jsp:include>
        </div>
    </div>
    <div class="c_cr">
        <div class="anniu">
            <span>最新</span>
            <span class="hotornew"><font><img src="/images/redd.png"/></font></span>
            <span>最热</span>
        </div>

        <div class="newst" id="newslist">

            <c:forEach var="n" items="${result.news()}">
                <div class="nlist" id="news_${n.getNewsId()}">
                    <div class="npic">
                        <a href="/action/news/view/${n.getNewsId()}">
                            <img src="http://resource.fxgold.com/${n.getNewsThumbnail()}"/>
                        </a>
                    </div>
                    <div class="nnr">
                        <h2><a href="/action/news/view/${n.getNewsId()}">${n.getNewsTitle()}</a></h2>

                        <div class="snstime">
                            <span class="zuoz">
                                <c:choose>
                                    <c:when test="${n.isNewsIsOrigin() == true}">
                                        文/<a>${n.getEditor().getEditorName()}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="文/${n.getNewsAuthor()}"/>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            <span class="time"><fmt:formatDate value="${n.getPublishtime()}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <span class="look"><c:out value="${n.getNewsView()}"/></span>
                            <span class="msg"><c:out value="${n.getNewsComment()}"/></span>
                            <span class="good"><c:out value="${n.getNewsLike()}"/></span>
                        </div>
                        <p><a href="/action/news/view/${n.getNewsId()}">${n.getNewsAbstract()}</a></p>
                    </div>
                </div>
            </c:forEach>

            <div class="page1">
                <c:choose>
                    <c:when test="${result.currentPage() <= 1}">
                        <a href="javascript:volid(0);" style="cursor:default">&lt;</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/action/news/subpage/${result.currentPage()-1}">&lt;</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${result.startPage()}" end="${result.endPage()}" step="1">
                    <c:choose>
                        <c:when test="${result.currentPage() == i+1}">
                            <a href="/action/news/subpage/${i+1}" class="active">${i+1}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/action/news/subpage/${i+1}">${i+1}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${result.currentPage() >= result.endPage()+1}">
                        <a href="javascript:volid(0);" style="cursor:default">&gt;</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/action/news/subpage/${result.currentPage()+1}">&gt;</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="c_rt">
        <div class="adnav">
            <div class="ad_1">
                <script>
                    (function() {
                        var s = "_" + Math.random().toString(36).slice(2);
                        document.write('<div id="' + s + '"></div>');
                        (window.slotbydup=window.slotbydup || []).push({
                            id: '2071900',
                            container: s,
                            size: '275,100',
                            display: 'inlay-fix'
                        });
                    })();
                </script>
            </div>
            <div class="ad_1">
                <script>
                    (function() {
                        var s = "_" + Math.random().toString(36).slice(2);
                        document.write('<div id="' + s + '"></div>');
                        (window.slotbydup=window.slotbydup || []).push({
                            id: '2071902',
                            container: s,
                            size: '275,100',
                            display: 'inlay-fix'
                        });
                    })();
                </script>
            </div>
            <div class="ad_1">
                <script>
                    (function() {
                        var s = "_" + Math.random().toString(36).slice(2);
                        document.write('<div id="' + s + '"></div>');
                        (window.slotbydup=window.slotbydup || []).push({
                            id: '2071904',
                            container: s,
                            size: '275,100',
                            display: 'inlay-fix'
                        });
                    })();
                </script>
            </div>
        </div>
    </div>
    <div style="clear:both;"></div>
</div>


<jsp:include page="../common/footer.jsp" />

<!--  广告位：新闻头部a1  -->
<script>
    (function()  {
        (window.slotbydup=window.slotbydup  ||  []).push({
            id:  '2071850',
            container:  "header-ad-1",
            size:  '350,75',
            display:  'inlay-fix'
        });
    })();
</script>

<!--  广告位：新闻头部a2  -->
<script>
    (function()  {
        (window.slotbydup=window.slotbydup  ||  []).push({
            id:  '2071852',
            container:  "header-ad-2",
            size:  '350,75',
            display:  'inlay-fix'
        });
    })();
</script>

<script type="text/javascript" src="/js/lib/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/js/lib/jquery.easing.min.js"></script>
<script type="text/javascript" src="/js/lib/jquery.sortElements.js"></script>
<script type="text/javascript" src="/js/news/subpage.js"></script>
<script type="text/javascript" src="/js/news/index.js"></script>

</body>
</html>