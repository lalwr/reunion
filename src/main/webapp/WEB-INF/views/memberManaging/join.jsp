<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title>Join</title>
</head>
<body>
<div class="container">
    <div class="row">

        <div class="col-md-8 col-md-offset-2">

            <form id = "uploadForm" role="form" method="POST" action="/member/signUp" enctype="multipart/form-data">

                <legend class="text-center">Register</legend>

                <fieldset>
                    <legend>Account Details</legend>

                    <div class="form-group col-md-6">


                        <label for="id">사용자 ID</label>
                        <input type="text" class="form-control" name="id" id = "id" placeholder="ID를 입력하세요"/><br>
                        <input type="button" value="ID 중복 확인" id = "idCheck" class="btn btn-primary"/><br>
                        <script>
                            var idCheckResult = false;
                            var pwCheckResult = false;
                            document.getElementById("idCheck").onclick = function(){
                                idCheck(document.getElementById("id").value, "idCheck");
                            }
                            function idCheck(str, url){
                                var req = new XMLHttpRequest();
                                req.onreadystatechange = function () {
                                    if(this.readyState == 4 && this.status == 200){
                                        idCheckResult = this.response == "true";
                                        if(idCheckResult){
                                            alert("사용 가능한 아이디입니다.")
                                        }else{
                                            alert("이미 등록된 아이디 입니다.")
                                        }
                                        if(idCheckResult && pwCheckResult){
                                            button.disabled = false;
                                        }else{
                                            button.disabled = true;
                                        }
                                    }
                                }
                                req.open("GET", url +"?id="+str);
                                req.send();
                            }
                        </script>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="pw">비밀번호</label>
                        <input type="password" class="form-control" name="password" id="pw" placeholder="비밀번호를 입력하세요">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="pwC">비밀번호 재입력</label>
                        <input type="password" class="form-control" name="pwVerify" id="pwC" placeholder="비밀번호 재입력"><br>
                        <input type="button" id="pwCheck" class="btn btn-primary" onclick="pwCheck_click();" value="비밀번호 확인" /><br>

                        <script>
                            function pwCheck_click() {
                                if(document.getElementById("pw").value == ""){
                                    alert("비밀번호 입력 필요");
                                }else if(document.getElementById("pw").value == document.getElementById("pwC").value){
                                    alert("비밀번호 확인");
                                    pwCheckResult = true;
                                    if(idCheckResult){
                                        button.disabled = false;
                                    }else{
                                        alert("아이디 중복 확인 요청");
                                        button.disabled = true;
                                    }
                                }else{
                                    alert("비밀번호 불일치");
                                    button.disabled = true;
                                }
                            }
                        </script>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="pw">성명</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="성명을 입력하세요">
                    </div>

                    <div class="form-group col-md-12">
                        <label for="school">출신 학교</label>
                        <select class="form-control" name="school" id="school">
                            <c:forEach var="school" items="${schools}">
                                <option>
                                        ${school.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </fieldset>

                <div class="form-group">
                    <div class="col-md-12">
                        <input type="file" name="uploadFile" id = "uploadFile">
                        <input type="submit" class="btn btn-primary" value ="회원가입" id="check"/><br>
                        <script>
                            var button = document.getElementById('check');
                            button.disabled = true;
                        </script>
                    </div>
                </div>

            </form>


        </div>

    </div>
</div>
</body>
</html>
