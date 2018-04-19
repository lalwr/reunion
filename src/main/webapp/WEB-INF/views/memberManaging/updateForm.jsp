<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Form</title>
</head>
<body>
현재 회원정보<br>
memberNo : ${info.no}<br>
id : ${info.id}<br>
name : ${info.name}<br>
school : ${info.schoolName}<br>
수정할 회원 정보 입력<br>
<form method="post" action ="/member/update">
    변경할 비밀번호 : <input type ="text" name ="password" id="pw" value><br>
    변경할 비밀번호 재입력 : <input type ="text" name ="passwordC" id="pwC" value>
    <input type="button" id="pwCheck" class="btn btn-primary" onclick="pwCheck_click();" value="비밀번호 확인" /><br>

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
    학교 :
    <select class="form-control" name="school" id="school">
        <c:forEach var="school" items="${schools}">
            <option>
                ${school.name}
            </option>
        </c:forEach>
    </select><br>
    <input type ="hidden" name="_method" value="PUT">
    <input type="submit" value ="회원정보 수정 확인" id="check"/>
    <script>
        var button = document.getElementById('check');
        button.disabled = true;
    </script>
</form>

</body>
</html>
