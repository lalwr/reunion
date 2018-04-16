<%@ page import="org.aspectj.weaver.ast.Var" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>댓글 수정</title>
</head>
<body>

<form action="/boardreply/update_reply/${boardNo}/${no}" method="post">
    <%
        String s = (String)request.getAttribute("content") ;
    %>
    <input type="hidden" name="boardno" value="${boardNo}">
    내용 : <textarea name="updatecontent" cols="40" rows="8"><%=s%></textarea><br>
    <input type="submit">

</form>

</body>
</html>
