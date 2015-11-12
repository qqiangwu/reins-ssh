<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>I am Header! <a href="/j_spring_security_logout">Logout</a> </div>
<hr/>
<c:set var="user">
    <security:authentication property="principal"/>
</c:set>
<div>Your Info: ${user}</div>
<hr/>