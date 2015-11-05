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

    <c:choose>
        <c:when test="${result.tags().size() > 0}">
            <title><c:out value="${result.news().getNewsTitle()}_${result.tags().get(0).getTagName()}新闻-金汇财经"/></title>
        </c:when>
        <c:otherwise>
            <title><c:out value="${result.news().getNewsTitle()}_新闻-金汇财经"/></title>
        </c:otherwise>
    </c:choose>
    <meta name="description" content="${result.news().getNewsAbstract()}"/>

    <link href="/css/index.css" rel="stylesheet" type="text/css"/>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="/js/lib/respond.min.js"></script>
    <script type="text/javascript" src="/js/lib/html5shiv.min.js"></script>
    <![endif]-->
    
    <script src="http://dup.baidustatic.com/js/ds.js"></script>

    <link href="/favicon.ico" rel="bookmark" type="image/x-icon"/>
    <link href="/favicon.ico" rel="icon" type="image/x-icon"/>
    <link href="/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <style type="text/css">
        /* 引用的样式 */
        blockquote {
            margin-bottom: 10px;
			padding: .6em 1em;
			border-left: .4em solid #cad4f2;
			background: #f0f0f0;
			margin-top: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="true"/>

<div id="context">
    <div class="c_lt">
        <div class="openn">+</div>
        <div class="clt_f">

            <h2>${pageType}<span class="colsen">X</span></h2>
            <jsp:include page="../common/navbar.jsp"></jsp:include>
        </div>
    </div>
    <div class="c_cr">
        <div class="news_x" id="newscontent">
            <h1><c:out value="${result.news().getNewsTitle()}"/></h1>

            <div class="snstime">
            	<span class="zuoz">
            		<c:choose>
                        <c:when test="${result.news().isNewsIsOrigin() == true}">
                            <c:out value="文/"/><a><c:out
                                value="${result.news().getEditor().getEditorName()}"/></a>
                        </c:when>
                        <c:otherwise>
                            <c:out value="文/${result.news().getNewsAuthor()}"/>
                        </c:otherwise>
                    </c:choose>
            	</span>
                <span class="time"><fmt:formatDate value="${result.news().getPublishtime()}"
                                                   pattern="yyyy-MM-dd HH:mm:ss"/></span>
                <span class="look"><c:out value="${result.news().getNewsView()}"/></span>
                <span class="msg"><c:out value="${result.news().getNewsComment()}"/></span>
                <span class="good"><c:out value="${result.news().getNewsLike()}"/></span>
            </div>
            <div class="bq">
                <span>标签：</span>
                <c:forEach var="tag" items="${result.tags()}">
                    <a><c:out value="${tag.getTagName()}"/></a>
                </c:forEach>
            </div>
        </div>

        <div class="newcontent">
            ${result.content()}
        </div>

        <!-- 
        <div class="newfx">
            <span>分享到：</span>
        </div>
         -->
    </div>
    <div class="c_rt">
        <div class="adnav">
        <div class="ad_1">
	        <script>
			(function() {
			    var s = "_" + Math.random().toString(36).slice(2);
			    document.write('<div id="' + s + '"></div>');
			    (window.slotbydup=window.slotbydup || []).push({
			        id: '2071890',
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
			        id: '2071892',
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
			        id: '2071894',
			        container: s,
			        size: '275,100',
			        display: 'inlay-fix'
			    });
			})();
			</script>
	        </div>
	    </div>

        <div class="ab_wz">
            <h2>相关文章</h2>
            <c:forEach var="n" items="${result.relatedNews()}">
                <h2><a href="${n.getNewsPath()}.html"><c:out value="${n.getNewsTitle()}"/></a></h2>
            </c:forEach>
        </div>
    </div>
    <div style="clear:both;"></div>
</div>


<jsp:include page="../common/footer.jsp" />

<!--  广告位：新闻头部a1  -->
<script>
    (function()  {
        (window.slotbydup=window.slotbydup  ||  []).push({
            id:  '2071886',
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
            id:  '2071888',
            container:  "header-ad-2",
            size:  '350,75',
            display:  'inlay-fix'
        });
    })();
</script>

<script type="text/javascript">
    //n2cj env
    var N2CJ = {
        newsId: function () {
            return ${result.news().getNewsId()};
        }
    };
</script>
<script type="text/javascript" src="/js/lib/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/js/lib/jquery.easing.min.js"></script>
<script type="text/javascript" src="/js/lib/jquery.sortElements.js"></script>
<script type="text/javascript" src="/js/news/index.js"></script>
<script type="text/javascript" src="/js/news/view.js"></script>
<script type="text/javascript">
    // onload script
    $(function () {
        viewCtrl.newsDetail(N2CJ.newsId());
        viewCtrl.addView(N2CJ.newsId());
    });
</script>
</body>
</html>