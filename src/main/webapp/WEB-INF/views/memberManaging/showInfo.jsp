<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Show Info</title>
</head>
<body>

<table class="table">
    <tbody>
    <p><img src="image/${profile.memberNo}" width="100" height="100"/><br></p>
    <p>memberNo : ${info.no}<br></p>
    <p>id : ${info.id}<br></p>
    <p>name : ${info.name}<br></p>
    <p>school : ${info.schoolName}<br></p>
    </tbody>
</table>
<form method="get" action ="/member/updateForm">
    <input type ="submit" value = "회원정보 수정"/><br>
</form>
<form method="post" action = "/member/delete">
    <input type ="submit" value = "회원 탈퇴하기">
    <input type ="hidden" name ="_method" value = "delete">
</form>
<form method="get" action = "/reunion/list">
    <input type ="submit" value = "홈으로 돌아가기">
</form>


</body>
</html>
