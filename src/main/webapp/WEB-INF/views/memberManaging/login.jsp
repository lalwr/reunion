<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/member/loginCheck">
    ${js}
    아이디 : <input type="text" name="id" id = "id"/><br>
    비밀번호 : <input type="password" name="password" id = "password"/><br>
    <input type="hidden" name = "referer" value ="${referer}">
    <input type="submit" value="로그인"/>
    <input type="button" onclick="location.href = '/member/join'" value = "회원가입"/>
</form>
</body>
</html>
