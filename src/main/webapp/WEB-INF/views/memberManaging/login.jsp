<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title>Login</title>
</head>
<body>
<form class="form-horizontal" method ="post" action ="/member/loginCheck">
    ${js}
    <fieldset>

        <!-- Form Name -->
        <legend>Login</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="id">사용자 ID</label>
            <div class="col-md-4">
                <input id="id" name="id" type="text" placeholder="" class="form-control input-md">
                <span class="help-block">ID를 입력하세요</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">비밀번호</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md">
                <span class="help-block">비밀번호를 입력하세요</span>
            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="buttonLogin"></label>
            <div class="col-md-8">
                <input type = "submit" id="buttonLogin" name="buttonLogin" class="btn btn-success" value = "로그인"/>
                <input tupe = "button" id="buttonRegistry" onclick="location.href = '/member/join'" name="buttonRegistry" class="btn btn-primary" value = "회원가입"/>
            </div>
        </div>

        <input type="hidden" name = "referer" value ="${referer}">
    </fieldset>
</form>

</body>
</html>
