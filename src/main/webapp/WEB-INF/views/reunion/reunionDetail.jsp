<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
$(document).ready(function()
{

});
function saveReunion() {
    document.getElementById("form").action = "${contextPath}/reunion/update";
    document.getElementById("method").value = "put";
    document.getElementById("form").submit();
}
function deleteReunion() {
    document.getElementById("form").action = "${contextPath}/reunion/delete";
    document.getElementById("method").value = "delete";
    document.getElementById("form").submit();
}
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 수정</title>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <h3 class="page-header">상세 화면</h3>
    </div>
</div>
<form id="form" name="form" method="post" action="">
<input type="hidden" name="_method" value="" id="method">
<input type="hidden" name="schoolNo" value="${result.schoolNo}">
<input type="hidden" name="categoryNo" value="${result.categoryNo}">
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
                                <input class="form-control" name="subject" value="${result.subject}" placeholder="제목">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <input class="form-control" name="content" value="${result.content}" placeholder="내용">
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="regId" value="${result.regId}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성자">
                            </div>
                            <div class="form-group">
                                <label>작성일</label>
                                <input class="form-control" name="regDate" value="${result.regDate}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성일">
                            </div>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
</form>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-body panel-view">
                <button type="button" class="btn btn-default" id="save" onclick="saveReunion()">저장</button>
                <button type="button" class="btn btn-default" id="delete" onclick="deleteReunion()">삭제</button>
            </div>
        </div>
    </div>
</div>

