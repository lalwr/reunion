<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
$(document).ready(function()
{

});
function updateReunion() {
    var page = document.getElementById('page').value;
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/detail/" + document.getElementById('no').value
        + "?page=" + page + "&searchType=" + serarchType + "&searchText=" + serarchText;
}
function listReunion() {
    var page = document.getElementById('page').value;
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/list?page=" + page + "&searchType=" + serarchType + "&searchText=" + serarchText;
}
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 상세</title>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <h3 class="page-header">상세 화면</h3>
    </div>
</div>
<input type="hidden" id="page" value="${condition.page}" />
<input type="hidden" id="searchType" value="${condition.searchType}" />
<input type="hidden" id="searchText" value="${condition.searchText}" />
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" id="no" name="no" value="${result.no}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="번호">
                            </div>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="subject" value="${result.subject}" readonly="readonly" placeholder="제목">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" name="content" id="content" rows="3" placeholder="내용" readonly="readonly">${result.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="regId" value="${result.regId}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성자">
                            </div>
                            <div class="form-group">
                                <label>작성일</label>
                                <input class="form-control" name="regDate" value="${result.regDate}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성일">
                            </div>
                            <c:forEach var="file" items="${files}" varStatus="status">
                            <div class="form-group">
                                <label>파일${status.count}</label>
                                <input class="form-control" name="file" value="${file.name}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="파일">
                                <c:if test="${file.format == 'image/png'}">
                                <label>이미지뷰어</label>
                                <img src="/reunion/image//${file.no}" class="img-Thumbnail" />
                                </c:if>
                            </div>
                            </c:forEach>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-body panel-view">
                <c:if test="${sessionScope.loginId == result.regId}">
                <button type="button" class="btn btn-default" id="update" onclick="updateReunion()">수정</button>
                </c:if>
                <button type="button" class="btn btn-default" id="list" onclick="listReunion()">목록</button>
            </div>
        </div>
    </div>
</div>
