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
    <ul class="nav nav-pills mb-3">
        <li class="nav-item">
            <a class="nav-link active" href="#">게시판</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Separated link</a>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#">Disabled</a>
        </li>

    </ul>
    <form class="d-flex" action="/board/search/1" method="get">
        <input class="form-control me-sm-2"   type="search" placeholder="Search" name="keyword">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
    <div class="float-end" style="float: left;">

        <a href="/user/update/${logIn.id}" class="btn btn-outline-light" >회원정보 수정하기</a>
    </div>
    <h1>${logIn.nickname}님 환영합니다</h1>
    <div class="row h-100 align-items-center">

        <div class="row">
            <div class="col">


                <table class="table table-hover mt-5">
                    <thead>
                    <tr class="table-info">
                        <th >번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="item">

                        <tr class="table-info" onclick="location.href='/board/showOne/${item.id}'">
                            <td>${item.id}</td>
                            <th>${item.title}</th>
                            <th>${item.writerNickname}</th>
                            <th>${item.entrydate}</th>
                            <th>${item.modifydate}</th>

                        </tr>

                    </c:forEach>
                    <tr>
                        <td colspan="5" class="justify-content-center" style="box-shadow:none !important;}">
                            <ul class="pagination" >
                                <li class="page-item">
                                    <a class="page-link" href="${pagingAddr}/1?keyword=${keyword}">&laquo</a>
                                </li>
                                <c:forEach var="i" begin="${paging.start}" end="${paging.end}">
                                    <c:choose>
                                        <c:when test="${i eq current}">
                                            <li class="page-item active disabled">
                                                <a class="page-link" href="${pagingAddr}/${i}?keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link" href="${pagingAddr}/${i}?keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li class="page-item">
                                    <a class="page-link" href="${pagingAddr}/${paging.totalPage}?keyword=${keyword}">&raquo</a>
                                </li>
                            </ul>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <a href="/board/insert/new" class="btn btn-outline-light m-2">글 등록하기</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
