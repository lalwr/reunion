<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/member/loginCheck">
    아이디 : <input type="text" name="id"/><br>
    비밀번호 : <input type="text" name="password"/><br>
    <input type="submit" value="로그인"/>
    <input type="button" onclick="location.href = '/member/join'" value = "회원가입"/>
</form>
</body>
</html>
