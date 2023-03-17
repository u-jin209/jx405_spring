<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: u
  Date: 2023-03-15
  Time: 오후 7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <link rel="stylesheet" as="style" crossorigin
          href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.6/dist/web/static/pretendard.css"/>
    <style type="text/css">
      body {
        font-family: Pretendard;
      }
    </style>
    </head>
<body>
  <div class="container">
    <div class="row h-100 align-items-center">
      <div class="row">
        <div class="col-10 align-content-center">
          <div class="row">
            <div class="col">
              <c:if test="${not empty message}">
                <div class="alert alert-dismissible alert-danger">
                  <h3>${message}</h3>
                </div>
              </c:if>
            </div>
          </div>

          <form method="post" action="/user/register">
            <div class="row">
              <div class="col">
                <label for="input-username">아이디</label>
                <input type="text" id = "input-username" name="username" class="form-control" ><br/>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label for="input-password">비밀번호</label>
                <input type="password"  id = "input-password" name="password" class="form-control"><br/>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <label for="input-nickname">닉네임</label>
                <input type="text"  id = "input-nickname" name="nickname" class="form-control"><br/>
                <button type="submit" class="btn btn-outline-light m-2 " >회원등록하기</button>
              </div>
            </div>

          </form>

        </div>
      </div>
    </div>
  </div>



</body>
</html>
