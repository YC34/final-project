<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>뉴스 상세</title>
</head>
<body>
    <h4> ${naverNews.company}</h4>
    <h1> ${naverNews.title}</h1>
    <p>${naverNews.newsDate}</p>
    <p><a href="${naverNews.url}">url</a></p>
    <p>${naverNews.contents}</p>

</body>
</html>
