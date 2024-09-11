<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> 쓰기 , 업데이트,삭제</h1>
    <form action="naverNews/write" method="post">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" required>

        <label for="content">내용:</label>
        <textarea id="content" name="content" rows="10" required></textarea>

        <button type="submit">작성 완료</button>
    </form>
</body>
</html>
