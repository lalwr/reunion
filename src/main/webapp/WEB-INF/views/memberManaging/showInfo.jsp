<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Show Info</title>
</head>
<body>
memberNo : ${info.no}<br>
id : ${info.id}<br>
name : ${info.name}<br>
school : ${info.schoolName}<br>
<a href="image/${profile.memberNo}">프로필 사진 보기</a>

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
