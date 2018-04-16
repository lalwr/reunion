<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">동창회 찾기</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:set var="name" value="${sessionScope.loginId}" />

            <c:choose>
                <c:when test="${name eq null}">
                    <li><a href="javascript:;" onclick="signUp()"><span class="glyphicon glyphicon-sign-up"></span> Sign Up</a></li>
                    <li><a href="javascript:;" onclick="login()"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="javascript:;" onclick="logOut()"><span class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>