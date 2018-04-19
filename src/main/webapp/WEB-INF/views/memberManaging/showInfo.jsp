<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Show Info</title>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <h3 class="page-header">상세 화면</h3>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>memberNo</label>
                            <input class="form-control" name="subject" value="${info.no}" placeholder="memberNo">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>id</label>
                            <input class="form-control" name="subject" value="${info.id}" placeholder="id">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>name</label>
                            <input class="form-control" name="subject" value="${info.name}" placeholder="name">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>school</label>
                            <input class="form-control" name="subject" value="${info.schoolName}" placeholder="school">
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
                <c:if test="${sessionScope.loginId == result.regId}">
                    <form method="get" action ="/member/updateForm">
                        <button type="submit" class="btn btn-default">회원정보 수정</button>
                    </form>
                    <form method="get" action ="/member/delete">
                        <button type="submit" class="btn btn-default">회원 탈퇴하기</button>
                        <input type ="hidden" name ="_method" value = "delete">
                    </form>
                    <form method="get" action ="/member/list">
                        <button type="submit" class="btn btn-default">홈으로 돌아가기</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

