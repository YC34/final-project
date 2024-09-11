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
                <button type="button" id="checkEmail">중복체크</button> <!-- 이메일 중복체크 버튼 -->

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
    <script>
            document.getElementById("checkEmail").addEventListener("click", function() {
                const email = document.getElementById("email").value;
                if (!email) {
                    alert("이메일을 입력해주세요.");
                    return;
                }

                // fetch를 통해 서버로 이메일 중복 체크 요청
                fetch("/users/checkEmail?email=${email}", {
                    method: "GET",
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("이메일 중복 체크에 실패했습니다.");
                        }
                        return response.json();  // JSON 응답 처리
                    })
                    .then(data => {
                        if (data.isAvailable) {
                            alert("사용 가능한 이메일입니다.");
                        } else {
                            alert("이미 사용 중인 이메일입니다.");
                        }
                    })
                    .catch(error => {
                        console.log(error.value)
                        console.log(response.body)
                        console.error("Error:", error);
                        alert("이메일 중복 체크 중 오류가 발생했습니다.");
                    });
            });


    </script>
</body>
</html>
