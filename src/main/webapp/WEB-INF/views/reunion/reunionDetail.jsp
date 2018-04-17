<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
$(document).ready(function()
{
    loadReply();
});
function saveReunion() {
    document.getElementById("form").action = "${contextPath}/reunion/update";
    document.getElementById("form").submit();
}
function deleteReunion() {
    document.getElementById("form").action = "${contextPath}/reunion/delete";
    document.getElementById("method").value = "delete";
    document.getElementById("form").submit();
}
function listReunion() {
    var page = document.getElementById('page').value;
    var serarchType = document.getElementById('searchType').value;
    var serarchText = document.getElementById('searchText').value;
    window.location.href = "${contextPath}/reunion/list?page=" + page + "&searchType=" + serarchType + "&searchText=" + serarchText;
}
function loadReply() {
    $.ajax({
        type: "GET",
        url: '/boardreply/list_reply/' + document.getElementById('no').value,
        data : { },
        success: function(data){
            $("#reply").html(data);
        },
        error: function(err) {

        }
    });

}
function fileDown(no) {
    window.location.href = "/reunion/download/"+no;
}
function deleteFile(no) {
    if (confirm("정말 삭제하시겠습니까??") == true){
        $.ajax({
            type: "GET",
            url: '/reunion/fileDelete/' + no,
            data : { },
            success: function(data){
                alert('삭제 성공하였습니다');
                window.location.reload();
            },
            error: function(err) {
                alert('삭제 실패하였습니다');
            }
        });
    }else{   //취소
        return false;
    }
}
function replyWrite(){
    $.ajax({
        type: "POST",
        url: '/boardreply/write_reply/' + document.getElementById('no').value,
        data : $("#formReply").serialize(),
        success: function(data){
            window.location.reload();
        },
        error: function(err) {
            alert("댓글 등록 실패");
        }
    });
}
function replyDelete(no) {
    $.ajax({
        type: "POST",
        url: '/boardreply/delete_reply/' + no,
        data : { boardNo : document.getElementById('no').value },
        success: function(data){
            window.location.reload();
        },
        error: function(err) {
            alert("댓글 삭제 실패");
        }
    });
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
<form id="form" name="form" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="_method" value="" id="method">
<input type="hidden" name="schoolNo" value="${result.schoolNo}">
<input type="hidden" name="categoryNo" value="${result.categoryNo}">
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
                                <input class="form-control" name="subject" value="${result.subject}" placeholder="제목">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" name="content" id="content" rows="3" placeholder="내용">${result.content}</textarea>
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
                                <label>파일${status.count}</label><br>
                                <a href="javascript:;" onclick="fileDown(${file.no}); return false;">${file.name}</a>
                                <c:if test="${sessionScope.loginId == result.regId}">
                                <button type="button" class="btn btn-default" id="fileDelete" onclick="deleteFile(${file.no})">삭제</button>
                                </c:if>
                            </div>
                            </c:forEach>
                            <c:if test="${fn:length(files) == 0}">
                            <div class="form-group">
                                <label for="file1">파일1</label>
                                <input type="file" class="form-control-file" id="file1" name="file">
                            </div>
                            </c:if>
                            <c:if test="${fn:length(files) == 0 || fn:length(files) == 1}">
                            <div class="form-group">
                                <label for="file2">파일2</label>
                                <input type="file" class="form-control-file" id="file2" name="file">
                            </div>
                            </c:if>
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
                <c:if test="${sessionScope.loginId == result.regId}">
                <button type="button" class="btn btn-default" id="save" onclick="saveReunion()">저장</button>
                <button type="button" class="btn btn-default" id="delete" onclick="deleteReunion()">삭제</button>
                </c:if>
                <button type="button" class="btn btn-default" id="list" onclick="listReunion()">목록</button>
            </div>
        </div>
    </div>
</div>
<div id="reply"></div>

