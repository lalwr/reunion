<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Form</title>
</head>
<body>
<h3>현재 회원정보</h3>
<table>
    <tbody>
    <p><img src="image/${profile.memberNo}" width="100" height="100"/><br></p>
    <p>memberNo : ${info.no}<br></p>
    <p>id : ${info.id}<br></p>
    <p>name : ${info.name}<br></p>
    <p>school : ${info.schoolName}<br></p>
    </tbody>
</table>
<h3>수정할 회원 정보</h3>
<form method="post" action ="/member/update">
    <p>
        변경할 비밀번호 : <input type ="text" name ="password" id="pw" value>
    </p>
    <p>
        변경할 비밀번호 재입력 : <input type ="text" name ="passwordC" id="pwC" value>
    </p>
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
    <p>
        학교 :
        <select class="form-control" name="school" id="school">
            <c:forEach var="school" items="${schools}">
                <option>
                        ${school.name}
                </option>
            </c:forEach>
        </select><br>
    </p>
    <input type ="hidden" name="_method" value="PUT">
    <input type="submit" value ="회원정보 수정 확인" id="check"/>
    <script>
        var button = document.getElementById('check');
        button.disabled = true;
    </script>
</form>

</body>
</html>
