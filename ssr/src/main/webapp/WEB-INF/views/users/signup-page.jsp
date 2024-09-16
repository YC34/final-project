<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../layouts/header.jsp"%>
<html>
<head>
    <title>signup-page</title>
    <link rel="stylesheet" type="text/css" href="/css/signup-page.css">
</head>
<body>
    <div class="signup-container">
        <form method="post" action="/users/signup" >
            <div class="signup-title">SIGN UP</div>
            <div class="signup-logo"></div>
            <div class="signup-form">
                <!-- Email 입력 -->
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." required>

                <!-- 비밀번호 입력 -->
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." required>

                <!-- 사용자명 입력 -->
                <label for="username">사용자명</label>
                <input type="text" id="username" name="username" placeholder="사용자명을 입력해주세요." required>

                <!-- 전화번호 입력 -->
                <label for="telNumber">전화번호</label>
                <input type="tel" id="telNumber" name="telNumber" placeholder="전화번호를 입력해주세요." required>
            </div>
            <div>
                <button type="submit">회원가입</button>
            </div>
        </form>
    </div>
</body>
</html>
