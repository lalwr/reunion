<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/components/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/components/bootstrap/css/bootstrap-theme.css" />
<script type="text/javascript" src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${contextPath}/resources/components/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function()
    {
        $("#save").click(function()
        {
            $.ajax({
                type : "POST",
                url : "${contextPath}/reunion/update",
                cache : false,
                data : $("#form").serialize(),
                success: function(response){
                    window.location.href = "${contextPath}/reunion/list";
                }
            });
        });
    });
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 상세</title>
</head>
<body>
<input type="hidden" name="schoolNo" value="${reunionContent.schoolNo}">
<input type="hidden" name="categoryNo" value="${reunionContent.categoryNo}">
<div class="row">
    <div class="col-lg-12">
        <h3 class="page-header">상세 화면</h3>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form id="form" name="form">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" id="funnelId" name="funnelId" value="${reunionContent.no}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="번호">
                            </div>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="funnelNm" value="${reunionContent.subject}" placeholder="제목">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <input class="form-control" name="funnelNm" value="${reunionContent.content}" placeholder="내용">
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="funnelNm" value="${reunionContent.regId}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성자">
                            </div>
                            <div class="form-group">
                                <label>작성일</label>
                                <input class="form-control" name="funnelNm" value="${reunionContent.regDate}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성일">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-info">
            <div class="panel-body panel-view">
                <button type="button" class="btn btn-default" id="save">저장</button>
            </div>
        </div>
    </div>
</div>
