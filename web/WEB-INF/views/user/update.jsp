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
<form method="post" action="/user/update">

  아이디 : ${result.username}
  <br/>
  닉네임 : <input type="text" name="nickname" value="${result.nickname}" ><br/>
  <button type="submit">회원수정하기</button> <br/>
  <button type="button" onclick="location.href='/user/delete/${result.id}'">회원삭제하기</button>

</form>
</body>
</html>
