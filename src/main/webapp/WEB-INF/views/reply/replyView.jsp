<%@ page import="org.aspectj.weaver.ast.Var" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="reunionUiLib" uri="reunionUiLib"%>
<html lang="ko">
<head>
    <title>댓글</title>
</head>
<body>
<div class="container" id="reunion">
    <form id="formReply" action="/boardreply/write_reply/${board_n}" method="post" name="textform">
        <textarea name="content" cols="40" rows="8"></textarea>
        <button type="button" class="btn btn-default" id="save" onclick="replyWrite()">등록</button>
    <table class="table table-hover">
        <tbody>
        <tr>
            <td>사용자</td>
            <td>댓글</td>
            <td>등록일자</td>
        </tr>

        <c:forEach var="list" items="${replyList}" varStatus="status">
            <tr>

                <%--<td><input class="form-control" name="subject" value="${list.no}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="번호"></td>--%>
                <td><input class="form-control" name="subject" value="${list.memberId}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="ID"></td>
                <td><input class="form-control" name="subject" value="${list.content}"  readonly="readonly" style="background-color: #dcdcdc;" placeholder="댓글"></td>
                <td><input class="form-control" name="subject" value="${list.editDate}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성일"></td>

                <c:if test="${list.memberId == 'oh'}">
                <%--<c:if test="${sessionScope.loginId == ${list.memberId}}">--%>
                    <td><button type="button" onclick="location.href='/boardreply/update/form/${list.boardNo}/${list.no}'">수정</button> </td>
                    <input type="hidden" name="no" value="${list.no}">
                    <input type="hidden" name="bno" value="${list.boardNo}">
                    <td><input type="button" value="삭제" onclick="location.href='/boardreply/delete_reply/${list.boardNo}/${list.no}' "></td>

                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>
</div>
</body>
</html>