<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form method="post" action="/signUp">
    아이디 :  <input type="text" name="id"/><br>
    비밀번호 :  <input type="password" name="password" id = "pw"/><br>
    비밀번호 재입력:  <input type="password" name="pwVerify" id = "pw2"/>
    <input type="button" id="button1" onclick="button1_click();" value="비밀번호 확인" /><br>
    <script>
        function button1_click() {
            if(document.getElementById("pw").value == document.getElementById("pw2").value){
                alert("비밀번호 확인");
                var button = document.getElementById('check');
                button.disabled = false;
            }else{
                alert("비밀번호 불일치");
                var button = document.getElementById('check');
                button.disabled = true;
            }
        }
    </script>
    성명 :  <input type="text" name="name"/><br>
    출신학교 :
    <select name = "school">
        <c:forEach var="school" items="${schools}">
            <option>
                    ${school.name}
            </option>
        </c:forEach>
    </select><br>
    <input type="submit" value ="회원가입" id="check"/>
    <script>
        var button = document.getElementById('check');
        button.disabled = true;
    </script>




</form>
</body>
<head>
    <title>Join</title>
</head>
</html>
