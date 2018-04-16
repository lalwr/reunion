<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="reunionUiLib" uri="reunionUiLib"%>
<script type="text/javascript">
$(document).ready(function() {

});
function loadGrid() {
    var page = document.getElementById('page').value;
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/list?page=" + page + "&searchType=" + serarchType + "&searchText=" + serarchText;

}
function pageNavigation(page) {
    document.getElementById('page').value = page;
    loadGrid();
}
function view(no) {
    var page = document.getElementById('page').value;
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/view/" + no
        + "?page=" + page + "&searchType=" + serarchType + "&searchText=" + serarchText;
}
function writeNew() {
    window.location.href = "${contextPath}/reunion/write";
}
function listSearch() {
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/list?searchType=" + serarchType + "&searchText=" + serarchText;
}
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 리스트</title>
</head>
<body>
<div class="container" id="reunion">
    <div class="row">
        <div class="col-lg-12">
            <form id="form" name="form">
                <div class="panel panel-info">
                    <div class="panel-heading">검색조건</div>
                    <input type="hidden" id="page" name="page" value="${condition.page}">
                    <div class="panel-body panel-search-body">
                        <div class="col-xs-6 col-md-4">
                            <div class="form-group">
                                <label></label>
                                <select class="form-control" name="searchType" id="searchType">
                                    <option value="">선택</option>
                                    <option value="subject" <c:if test="${condition.searchType == 'subject'}">selected="selected"</c:if> />제목</option>
                                    <option value="content" <c:if test="${condition.searchType == 'content'}">selected="selected"</c:if> />내용</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-8">
                            <div class="form-group">
                                <label></label>
                                <input type="text" class="form-control" id="searchText" name="searchText" size="20" value="${condition.searchText}" placeholder="검색할 내용">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="panel-footer panel-search-btn">
                <button type="button" class="btn btn-outline btn-primary btn-sm" id="search" onclick="listSearch()">조회</button>
            </div>
        </div>
    </div>
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
                <td><a href="javascript:;" onclick="view(${list.no}); return false;">${list.subject}</a></td>
                <td>${list.content}</td>
                <td>${list.regId}</td>
                <td>${list.regDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="button" class="btn btn-default" onclick="writeNew()">작성</button>
    <reunionUiLib:paging linkFunction="pageNavigation" pagingInfo="${pagingInfo}" />
</div>

