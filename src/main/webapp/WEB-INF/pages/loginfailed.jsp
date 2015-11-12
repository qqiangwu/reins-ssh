<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: qqiangwu
  Date: 11/11/15
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
        <meta http-equiv="refresh" content="0; url=/index.do"/>
    </security:authorize>
    <title>Login Failed</title>
</head>
<body>
    <a href="/login.do">Login failed</a>
</body>
</html>