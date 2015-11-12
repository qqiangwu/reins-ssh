<%--
  Created by IntelliJ IDEA.
  User: qqiangwu
  Date: 11/11/15
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <jsp:include page="pagelet/header.jsp" flush="true"/>

    This is the index page, only login user can see this page!

    <p>The following are further authorized pages</p>
    <ul>
        <li><a href="/user/admin.do">Admin</a></li>
        <li><a href="/user/user.do">User</a></li>
        <li><a href="/note/main.do">NoteList: Admin + User</a></li>
    </ul>

    <jsp:include page="pagelet/footer.jsp" flush="true"/>
</body>
</html>
