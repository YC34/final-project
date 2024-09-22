<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layouts/header.jsp" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/signup-page.css">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <div class="signup-container mx-auto mt-5 p-4 border rounded shadow">
        <h2 class="text-center mb-4">Sign Up</h2>
        <form method="post" action="/users/signup">
            <!-- Email input -->
            <div class="input-group mb-3">
                <input type="email" id="email" name="email" class="form-control" placeholder="📧 이메일을 입력해주세요." required>
            </div>
            <!-- Password input -->
            <div class="input-group mb-3">
                <input type="password" id="password" name="password" class="form-control" placeholder="🔒 비밀번호를 입력해주세요." required>
            </div>

            <!-- Username input -->
            <div class="input-group mb-3">
                <input type="text" id="membername" name="membername" class="form-control" placeholder="👤 사용자명을 입력해주세요." required>
            </div>

            <!-- Phone number input -->
            <div class="input-group mb-3">
                <input type="tel" id="telNumber" name="telNumber" class="form-control" placeholder="📞 전화번호를 입력해주세요." required>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary signup-button">회원가입</button>
            </div>
        </form>
    </div>
    <ul>
        <li><a href="/">🏠홈으로</a> &nbsp&nbsp&nbsp⎮</li>
        <li><a href="/users/login">✍️ 로그인</a></li>
    </ul>
</div>

</body>
</html>
