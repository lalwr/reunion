<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="ko">
<head>
    <title>댓글</title>
</head>
<body>
<form id="form" action="/boardreply/write_reply" method="post" name="textform">
    <input type="hidden" name="_method" value="" id="method">
    <table class="table">
        <tr>
            <%--<td>사용자: <input type="text" id="memberId" name="memberId"></td>--%>
            <td><textarea name="content" cols="40" rows="8"></textarea></td>
            <td><input type="submit" value="등록"></td>
        </tr>
        <tbody>
        <tr>
            <td>글번호</td>
            <td>사용자</td>
            <td>댓글</td>
            <td>등록일자</td>
        </tr>
        <c:forEach var="list" items="${replyList}" varStatus="status">
            <tr>
                <td>${list.no}</td>
                <td>${list.memberId}</td>
                <td>${list.content}</td>
                <td>${list.regDate}</td>
                <%--<c:if test="${memberId eq 'oh'}">--%>
                <td><input type="button" id="button1" onclick="button1_click();" value="수정"/></td>
                <input type=hidden name="no" value="${list.no}">
                <td><input type="button" value="삭제" onclick="location.href='delete_reply/${list.no}'"></td>
                <%--<td><a href="delete.jsp?no=${list.no}">삭제</a></td>--%>

                <%--</c:if>--%>

            </tr>
        </c:forEach>
        </tbody>

    </table>

</form>
</body>
</html>