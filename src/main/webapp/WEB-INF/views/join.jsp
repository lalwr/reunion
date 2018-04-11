<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form method="post" action="/login">
    아이디 :  <input type="text" name="id"/><br>
    비밀번호 :  <input type="password" name="password"/><br>
    비밀번호 재입력:  <input type="password" name="passwordVerify"/>
    <input type="button" value="비밀번호 확인"/><br>
    성명 :  <input type="text" name="name"/><br>
    출신학교 :
    <select>
        <c:forEach var="school" items="${schools}">
            <option>
                    ${school.name}
            </option>
        </c:forEach>
    </select>
    <br><input type="submit" value ="회원가입"/>
</form>
</body>
<head>
    <title>Join</title>
</head>
</html>
