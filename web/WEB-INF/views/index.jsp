<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: u
  Date: 2023-03-15
  Time: 오전 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>인덱스</title>
</head>
<body>
<form method="post" action="/user/auth">
    아이디 : <input type="text" name="username" >
    <br/>
    비밀번호 : <input type="text" name="password" >
    <button type="submit">로그인</button>
</form>
${message}
<a href="/user/register/new">회원 가입하기 </a>
</body>
</html>
