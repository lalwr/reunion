<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="ko">
<head>
    <title>댓글</title>
</head>
<body>
<form id="formReply" action="/boardreply/write_reply/${board_n}" method="post" name="textform">
        <textarea name="content" cols="40" rows="8"></textarea>
        <button type="button" class="btn btn-default" id="save" onclick="replyWrite()">등록</button>
    <table class="table">
        <tbody>
        <tr>
            <td>사용자</td>
            <td>댓글</td>
            <td>등록일자</td>
            <td></td>
        </tr>
        <c:forEach var="list" items="${replyList}" varStatus="status">
            <tr>
                <td>${list.memberId}</td>
                <td>${list.content}</td>
                <td>${list.editDate}</td>
                <c:if test="${list.memberId eq sessionScope.loginId}">
                    <td><input type="button" value="삭제" onclick="replyDelete(${list.no})"></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</form>
</body>
</html>