<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
$(document).ready(function()
{

});
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 작성</title>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <h3 class="page-header">상세 화면</h3>
    </div>
</div>
<form id="form" name="form" method="post" action="${contextPath}/reunion//write" enctype="multipart/form-data">
    <input type="hidden" name="schoolNo" value="${reunion.schoolNo}">
    <input type="hidden" name="categoryNo" value="${reunion.categoryNo}">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" id="no" name="no" readonly="readonly" style="background-color: #dcdcdc;" placeholder="번호">
                            </div>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="subject"  placeholder="제목">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <input class="form-control" name="content"  placeholder="내용">
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="regId" value="${sessionScope.loginId}" readonly="readonly" style="background-color: #dcdcdc;" placeholder="작성자">
                            </div>
                            <div class="form-group">
                                <label for="file1">File1</label>
                                <input type="file" class="form-control-file" id="file1" name="file">
                            </div>
                            <div class="form-group">
                                <label for="file2">File2</label>
                                <input type="file" class="form-control-file" id="file2" name="file">
                            </div>
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
                    <input type="submit" class="btn btn-default" id="save">
                </div>
            </div>
        </div>
    </div>
</form>
