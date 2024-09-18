
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../layouts/header.jsp"%>
<html>
<head>
    <title>skill-page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/skill-page.css">
</head>
<body>

<div class="container card-container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- Back End Card -->
        <div class="col">
            <div class="card h-100">
                <div class="card-header">📁 Back End</div>
                <div class="card-body">
                    <h4 class="card-title">Back End Skill</h4>
                    <p class="card-text">
                        • JAVA (Version : 17) - 백엔드 기초 언어<br/>
                        • SPRING BOOT (Version : 3.3.2) - 백엔드 프레임워크<br/>
                        • SPRING SECURITY - 인증과 인가<br/>
                        • JWT (Json Web Tokens) - 보안 도구<br/>
                        • MYBATIS - DB 연동 프레임워크<br/>
                        • JSP - SSR 이해<br/>
                        • RDBMS - Postgresql, MySQL, Oracle<br/>
                        • Linux - 서버 배포 및 제어<br/>
                    </p>
                    <h4 class="experience-title">Experience</h4>
                    <p class="card-text">
                        • Spring Boot Security 적용<br/>
                        • Spring Boot + JSP 개발<br/>
                        • DB 설계 및 적용<br/>
                        • 리눅스 서버 개발 및 배포<br/>
                        • MVC 아키텍처 패키지 구조화<br/>
                        • 데이터 제공 웹사이트 제작<br/>
                    </p>
                    <p><a href="https://github.com/YC34/final-project/tree/master/ssr">SSR CODE - github</a></p>
                </div>
            </div>
        </div>

        <!-- Data Card -->
        <div class="col">
            <div class="card h-100">
                <div class="card-header">📈 Data</div>
                <div class="card-body">
                    <h4 class="card-title">데이터 수집 및 정제</h4>
                    <p class="card-text">
                        • Python - 데이터 기초 언어<br/>
                        • 크롤링 - 데이터 수집 기술<br/>
                        • Excel to DB, CSV to DB - 문서 DB 자동화<br/>
                        • MYBATIS - DB 연동 프레임워크<br/>
                        • matplotlib 등 - 데이터 시각화<br/>
                        • ML - 이상 탐지 알고리즘<br/>
                        • 기초 통계 이해<br/>
                    </p>
                    <h4 class="experience-title">Experience</h4>
                    <p class="card-text">
                        • Spring Batch로 API 데이터 수집<br/>
                        • Python 크롤링으로 데이터 수집<br/>
                        • 데이터 정제를 위한 Data Cleansing<br/>
                        • 정제된 데이터 시각화<br/>
                        • LOF 알고리즘을 사용한 이상 탐지<br/>
                    </p>
                </div>
            </div>
        </div>

        <!-- Front & ENV Card -->
        <div class="col">
            <div class="card h-100">
                <div class="card-header">🏞️ Front & ENV</div>
                <div class="card-body">
                    <h4 class="card-title">프론트 및 개발 환경</h4>
                    <p class="card-text">
                        • Docker - 빠른 환경 구축 도구<br/>
                        • IntelliJ, Eclipse - 개발 도구<br/>
                        • GitHub, Git - 코드 형상 관리 도구<br/>
                        • Crontab - 스케줄 관리 도구<br/>
                        • JS - 동적 페이지 개발 도구<br/>
                        • React - CSR(Client Side Render) 도구<br/>
                    </p>
                    <h4 class="experience-title">Experience</h4>
                    <p class="card-text">
                        • 청소년 웹페이지 개발<br/>
                        • JSP 기반 웹페이지 개발<br/>
                        • Thymeleaf 기반 웹페이지 개발<br/>
                        • React로 화면 개발<br/>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
