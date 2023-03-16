<%--
  Created by IntelliJ IDEA.
  User: u
  Date: 2023-03-15
  Time: 오후 3:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인 성공!!!!!!</title>
</head>
<body>
<a href="/user/update/${logIn.id}">회원정보 수정하기</a>
<h1>${logIn.nickname}님 환영합니다</h1>

    <c:forEach items="${list}" var="item">
        <a href="/board/showOne/${item.id}"> ${item.id} ${item.title} ${item.entrydate}</a><br/>
    </c:forEach>

<a href="/board/insert/new">글 등록하기</a>
</body>
</html>
