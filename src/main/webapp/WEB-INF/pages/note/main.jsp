<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qqiangwu
  Date: 11/12/15
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="../pagelet/header.jsp" flush="true"/>

    <h1>Note List:</h1>
    <ul>
        <c:forEach var="note" items="${result.getNotes()}">
            <li>${note.getTitle()} ---- ${note.getContent()}</li>
        </c:forEach>
    </ul>

    <hr/>
    <h1>Add Note Via Ajax</h1>
    <form>
        <input id="note-title" placeholder="title" type="text" name="title">
        <input id="note-content" placeholder="content" type="text" name="content">
        <input id="note-submit" type="submit">
    </form>
    <hr/>
    <jsp:include page="../pagelet/footer.jsp" flush="true"/>

    <script src="/js/lib/jquery-1.11.3.min.js"></script>
    <script src="/js/note/noteList.js"></script>
</body>
</html>
