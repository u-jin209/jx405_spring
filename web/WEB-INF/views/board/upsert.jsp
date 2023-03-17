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
<form method="post" action="/board/upsert">
    <input type="hidden" value="${board.id}" name="id">
    <div class="container">
        <div class="row h-100 align-items-center">
            <div class="row mb-1">
                <div class="col">

                    <label for="form-title">제목 :</label>
                    <input  class="form-control" type="text" id= "form-title" value="${board.title}" name="title">

                </div>
            </div>
            <div class="row mb-3">
                <div class="col">

                    <label for="form-content">내용 :</label>
                    <input  class="form-control" type="text"  id="form-content" value="${board.content}" name="content">
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <button class="btn btn-outline-light mt-5" type="submit"> 등록하기 </button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
