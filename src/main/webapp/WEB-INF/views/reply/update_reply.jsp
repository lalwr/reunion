
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>댓글 수정</title>
</head>
<body>

<form action="/boardreply/update_reply/${no}" method="post">
    내용 : <textarea name="updatecontent" cols="40" rows="8"></textarea><br>
    <input type="submit">

</form>

</body>
</html>
