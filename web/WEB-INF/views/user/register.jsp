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
    <title>Title</title>
</head>
<body>
<form method="post" action="/user/register">
  아이디 : <input type="text" name="username" >
  <br/>
  비밀번호 : <input type="text" name="password" >
  <br/>
  닉네임 : <input type="text" name="nickname" >
  <button type="submit">회원등록하기</button>
</form>
</body>
</html>
