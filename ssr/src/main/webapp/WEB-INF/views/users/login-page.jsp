<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../layouts/header.jsp"%>
<html>
<head>
    <title>Login-page</title>
    <link rel="stylesheet" href="/css/login-page.css" type="text/css">
</head>
<body>
<div class="login-container">
    <form method="post" action="/loginProc" >
            <div class="login-title">LOGIN</div>
            <div class="login-logo"></div>
            <div class="user-input">
              <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." required>
              <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." required>
            </div>
            <div>
                <button type="submit">로그인</button>
            </div>
    </form>

</div>
<ul>
    <li><a href="/">🏠홈으로</a> &nbsp&nbsp&nbsp⎮</li>
    <li><a href="/users/signup">✍️ 회원가입</a></li>
</ul>

</body>
</html>
