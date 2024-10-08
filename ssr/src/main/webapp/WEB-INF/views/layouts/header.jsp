<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<html>
<head>
    <title>YOUNGCHAN PAGE</title>
<%--    <c:if test="${not empty errorMessage}">--%>
<%--        <script>--%>
<%--            alert("${errorMessage}");--%>
<%--            <c:remove var="errorMessage" scope="session"/>--%>
<%--        </script>--%>
<%--    </c:if>--%>

<%--    <c:if test="${not empty successMessage}">--%>
<%--        <script>--%>
<%--            alert("${successMessage}");--%>
<%--            <c:remove var="successMessage" scope="session"/>--%>
<%--        </script>--%>
<%--    </c:if>--%>
</head>
<body>
<div class="nav-container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">👨‍💻</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/skill">skill</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/naver/list">news</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/summary">summary</a>
                    </li>
                </ul>
            </div>
        </div>
        <c:if test="${empty sessionScope.email}">
            <!-- 로그인 하지 않은 상태 -->
            <form method="get" action="/users/login">
                <button type="submit" class="btn btn-outline-info" style="margin-right: 5px; font-weight: bolder;">Login</button>
            </form>
            <form method="get" action="/users/signup">
                <button type="submit" class="btn btn-outline-primary" style="font-weight: bolder; margin-right: 20px">SignUp</button>
            </form>
        </c:if>

        <c:if test="${not empty sessionScope.email}">
            <!-- 로그인 한 상태 -->
<%--            <form method="get" action="/users/mypage">--%>
<%--                <button type="submit" class="btn btn-outline-success" style="margin-right: 5px; font-weight: bolder;">Mypage</button>--%>
<%--            </form>--%>
            <form method="post" action="/users/logout">
                <button type="submit" class="btn btn-outline-warning" style="font-weight: bolder; margin-right: 20px">Logout</button>
            </form>
        </c:if>
    </nav>
</div>
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
            <c:remove var="errorMessage" scope="session"/>
        </script>
    </c:if>

    <c:if test="${not empty successMessage}">
        <script>
            alert("${successMessage}");
            <c:remove var="successMessage" scope="session"/>
        </script>
    </c:if>
</body>
</html>
