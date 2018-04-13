<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>댓글</title>
</head>
<body>
<form action="replyList" method="post" name="textform">
    <table class="table">
        <tr>
            <td>사용자: </td>
            <td><textarea name="content" cols="40" rows="8" ></textarea></td>
            <td><input type="submit" value="등록"></td>
        </tr>
        <tbody>
        <c:forEach var="list" items="${replyList}" varStatus="status">
            <tr>
                <td>${list.memberId}</td>
                <td>${list.content}</td>
                <td>${list.regDate}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</form>
</body>
</html>