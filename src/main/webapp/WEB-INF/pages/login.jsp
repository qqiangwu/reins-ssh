<%--
  Created by IntelliJ IDEA.
  User: qqiangwu
  Date: 11/11/15
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
</head>
<body>
    Login Page
<form method="post" action="/j_spring_security_check">
    <label>Username</label>
    <input type="text" placeholder="User Name" name="username">
    <label>Password</label>
    <input type="password" placeholder="Password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>
