<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="reunionUiLib" uri="reunionUiLib"%>
<script type="text/javascript">
$(document).ready(function() {

});
function loadGrid() {
    window.location.href = "${contextPath}/reunion/list?page=" + document.getElementById('page').value;
}
function pageNavigation(page) {
    document.getElementById('page').value = page;
    loadGrid();
}
function detail(no) {
    window.location.href = "${contextPath}/reunion/detail/" + no;
}
function detailNew() {
    window.location.href = "${contextPath}/reunion/detail/new";
}
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 리스트</title>
</head>
<body>
<div class="container" id="reunion">
    <form id="form" name="form">
    <input type="hidden" id="page" name="page" value="${reunion.page}">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${reunionList}" varStatus="status">
            <tr>
                <input type="hidden" name="schoolNo" value="${list.schoolNo}">
                <input type="hidden" name="categoryNo" value="${list.categoryNo}">
                <td>${list.no}</td>
                <td><a href="javascript:;" onclick="detail(${list.no}); return false;">${list.subject}</a></td>
                <td>${list.content}</td>
                <td>${list.regId}</td>
                <td>${list.regDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>
    <button type="button" class="btn btn-default" onclick="detailNew()">작성</button>
    <reunionUiLib:paging linkFunction="pageNavigation" pagingInfo="${pagingInfo}" />
</div>

