<%--
  Created by IntelliJ IDEA.
  User: life4honor
  Date: 2018. 4. 11.
  Time: PM 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/">
    아이디 : <input type="text" name="id"/><br>
    비밀번호 : <input type="text" name="password"/><br>
    <input type="submit" value="로그인"/>
    <input type="button" onclick="location.href = '/join'" value = "회원가입"/>
</form>

${id}
${name}
${password}
${school}
</body>
</html>
