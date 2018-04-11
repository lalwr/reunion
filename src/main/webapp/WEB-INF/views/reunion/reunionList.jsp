<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/components/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/components/bootstrap/css/bootstrap-theme.css" />
<script type="text/javascript" src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${contextPath}/resources/components/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#reunion").find("#detail a").click(function() {
            window.location.href = "${contextPath}/reunion/content/" + $(this).siblings("#reunionNo").val();
            return false;
        });
    });
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 리스트</title>
</head>
<body>
<div class="container" id="reunion">
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
                <td id="detail">
                    <a href="javascript:;">${list.no}</a>
                    <input type="hidden" id="reunionNo" value="${list.no}">
                </td>
                <td>${list.subject}</td>
                <td>${list.content}</td>
                <td>${list.regId}</td>
                <td>${list.regDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="button" class="btn btn-default" id="">글 작성</button>
</div>