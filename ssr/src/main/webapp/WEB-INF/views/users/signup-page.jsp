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
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button">Button</button>
                </div>
                <span id="email-check-result" class="form-text ms-2"></span>
            </div>
            <!-- Password input -->
            <div class="input-group mb-3">
                <input type="password" id="password" name="password" class="form-control" placeholder="🔒 비밀번호를 입력해주세요." required>
                <span class="form-text ms-2"></span>
            </div>

            <!-- Username input -->
            <div class="input-group mb-3">
                <input type="text" id="username" name="username" class="form-control" placeholder="👤 사용자명을 입력해주세요." required>
                <span id="username-check-result" class="form-text ms-2"></span>
            </div>

            <!-- Phone number input -->
            <div class="input-group mb-3">
                <input type="tel" id="telNumber" name="telNumber" class="form-control" placeholder="📞 전화번호를 입력해주세요." required>
                <span id="phone-check-result" class="form-text ms-2"></span>
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



<script>
    function validateEmail(email) {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailPattern.test(email);
    }

    function checkEmail() {
        const emailInput = document.getElementById('email');
        const email = emailInput.value.trim();
        const resultSpan = document.getElementById('email-check-result');

        if (!email) {
            resultSpan.textContent = '이메일을 입력해주세요.';
            resultSpan.style.color = 'red';
            return;
        }

        if (!validateEmail(email)) {
            resultSpan.textContent = '유효한 이메일 주소를 입력해주세요.';
            resultSpan.style.color = 'red';
            return;
        }
        console.log(email)
        const url = "/users/checkEmail?email="+ email;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                if (data.isAvailable === false) {
                    resultSpan.textContent = '사용 가능한 이메일입니다.';
                    resultSpan.style.color = 'green';
                } else {
                    resultSpan.textContent = '이미 사용 중인 이메일입니다.';
                    resultSpan.style.color = 'red';
                }
            })
            .catch(error => {
                console.error('이메일 중복 확인 오류:', error);
                resultSpan.textContent = '이메일 중복 확인 중 오류가 발생했습니다.';
                resultSpan.style.color = 'red';
            });
    }

    document.getElementById('signup-form').addEventListener('submit', function(event) {
        if (!emailIsAvailable) {
            event.preventDefault();  // 이메일이 중복이면 폼 제출을 막음
            alert('이메일 중복 확인을 완료해주세요.');
        }

    });

</script>

</body>
</html>
