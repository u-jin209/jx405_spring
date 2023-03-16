<%--
  Created by IntelliJ IDEA.
  User: u
  Date: 2023-03-15
  Time: 오후 5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/board/insert">

    제목 : <input type="text" name="title" ><br/>
    내용 : <input type="text" name="content"><br/>

    <button type="submit"> 등록하기 </button>
</form>
</body>
</html>
