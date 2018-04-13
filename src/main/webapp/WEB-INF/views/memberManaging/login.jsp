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
    아이디 : <input type="text" name="id" id = "id"/><br>
    비밀번호 : <input type="password" name="password" id = "password"/><br>
    <input type="button" value="로그인" id = "logTry"/>
    <script>
        document.getElementById("logTry").onclick = function(){
            logTry(document.getElementById("id").value, document.getElementById("password").value, "loginCheck");
        }

        function logTry(str1, str2, url){
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(this.readyState == 4 && this.status == 200){
                    if("success" == this.response){
                        var msg = "환영합니다. " + document.getElementById("id").value + "님";
                        alert(msg);
                        window.location.href = "/reunion/list";
                    }else if("incorrectPw" == this.response){
                        alert("비밀번호가 일치하지 않습니다.");
                    }else{
                        alert("아이디가 존재하지 않습니다.");
                    }
                }
            }
            req.open("POST", url);
            req.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
            var data = 'id=' + str1 + '&password=' + str2;
            req.send(data);
        }

    </script>
    <input type="button" onclick="location.href = '/member/join'" value = "회원가입"/>
</form>
</body>
</html>
