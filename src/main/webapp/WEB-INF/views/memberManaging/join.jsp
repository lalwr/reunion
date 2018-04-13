<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Join</title>
</head>
<body>

<form method="post" action="/member/signUp">
    아이디 :  <input type="text" name="id" id = "id"/>
    <input type="button" value="중복 확인" id = "idCheck"/><br>
    <script>
        document.getElementById("idCheck").onclick = function(){
            idCheck(document.getElementById("id").value, "idCheck");
        }
        function idCheck(str, url){
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(this.readyState == 4 && this.status == 200){
                    alert(this.response);
                }
            }
            req.open("GET", url +"?id="+str);
            req.send();
        }
    </script>

    성명 :  <input type="text" name="name"/><br>
    비밀번호 :  <input type="password" name="password" id = "pw"/><br>
    비밀번호 재입력:  <input type="password" name="pwVerify" id = "pwC"/>
    <input type="button" id="pwCheck" onclick="pwCheck_click();" value="비밀번호 확인" /><br>

    <script>
        function pwCheck_click() {
            if(document.getElementById("pw").value == ""){
                alert("비밀번호 입력 필요");
            }else if(document.getElementById("pw").value == document.getElementById("pwC").value){
                alert("비밀번호 확인");
                button.disabled = false;
            }else{
                alert("비밀번호 불일치");
                button.disabled = true;
            }
        }
    </script>
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
</html>
