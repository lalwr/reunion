<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" />
    <meta name="description" content="withx">
    <meta name="author" content="withx">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/components/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#back").click(function() {
                history.back(-1);
            });
        });
    </script>
</head>
<body>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"></h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-info">
                <div class="panel-heading">에러 페이지</div>
                <div class="panel-body panel-search-body" style="min-height: 80px;">
                    에러가 발생하였습니다. 관리자에게 문의해 주세요.<br><br>${exception}<br><br>
                </div>
                <div class="panel-footer" style="text-align: center;">
                    <button type="button" class="btn btn-outline btn-primary btn-sm" id="back">돌아가기</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>