<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../layouts/header.jsp" %>
<html>
<head>
    <title>뉴스 상세</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/news-detail-page.css">
</head>
<body>
<div class="news-container">
    <h4 class="news-company">${naverNews.company}</h4>
    <p class="news-date">${naverNews.newsDate}</p>
    <img src="${naverNews.imgUrl}" alt="뉴스 이미지" class="news-img" />
    <h1 class="news-title">${naverNews.title}</h1>
    <p class="news-contents">${naverNews.contents}</p>
    <p><a href="${naverNews.url}" class="btn btn-primary" target="_blank">기사 원문 보기</a></p>

    <!-- 댓글 섹션 -->
    <div class="comments-section">
        <h3>댓글</h3>
        <c:choose>
            <c:when test="${not empty sessionScope.email}">
                <!-- 로그인이 된 경우 댓글 작성 폼 -->
                <form action="comment/write" method="post" class="mb-4">
                    <div class="mb-3">
                        <textarea class="form-control" name="content" rows="3" placeholder="댓글을 입력하세요..." required></textarea>
                    </div>
                    <input type="hidden" name="userEmail" value="${sessionScope.email}">
                    <input type="hidden" name="boardId" value="${naverNews.naverNewsSequence}">
                    <input type="hidden" name="parentCommentId" value=""> <!-- 일반 댓글은 null로 가져간다. -->
                    <c:if test="${not empty commentList}">
                        <c:forEach var="comment" items="${commentList}">
                            <input type="hidden" name="commentId" value="${comment.commentUid}">
                        </c:forEach>
                    </c:if>
                    <button type="submit" class="btn btn-success">댓글 달기</button>
                </form>
            </c:when>
            <c:otherwise>
                <!-- 로그인이 안 된 경우 -->
                <p class="alert alert-warning">댓글을 작성하려면 <a href="users/login">로그인</a>이 필요합니다.</p>
            </c:otherwise>
        </c:choose>
        <div class="comment-list">
            <h5>이전 댓글</h5>
            <ul class="list-group">
                <c:forEach var="comment" items="${commentList}">
                    <li class="list-group-item">
                        <strong>${comment.username}</strong>: ${comment.content}
                        <c:set var="isUserComment" value="${sessionScope.email eq comment.userEmail}" />
                        <c:if test="${isUserComment}">
                            <form action="comment/delete" method="post" style="display:inline;">
                                <input type="hidden" name="commentUid" value="${comment.commentUid}">
                                <button type="submit" class="btn btn-danger btn-sm ms-2">삭제</button>
                            </form>
                        </c:if>

                        <div class="text-muted small">${comment.createAt}</div>
                        <!-- 대댓글 작성 폼 -->
                        <c:if test="${not empty sessionScope.email}">
                            <div>
                                <form class="reply-form mt-2" id="reply-form-${comment.commentUid}" action="comment/write" method="post">
                                    <div class="mb-3">
                                        <textarea class="form-control" name="content" rows="2" placeholder="답글을 입력하세요..." required></textarea>
                                    </div>
                                    <input type="hidden" name="userEmail" value="${sessionScope.email}">
                                    <input type="hidden" name="boardId" value="${naverNews.naverNewsSequence}">
                                    <input type="hidden" name="parentCommentId" value="${comment.commentUid}"> <!-- 대댓글이므로 parentCommentId에 해당 댓글의 uid 설정 -->
                                    <button type="submit" class="btn btn-secondary">답글 달기</button>
                                </form>
                            </div>
                        </c:if>

                        <!-- 대댓글 리스트 -->
                        <ul id="replies-${comment.commentUid}" class="list-group reply-section mt-2">
                            <c:if test="${not empty replyList[comment.commentUid]}">
                                <c:forEach var="reply" items="${replyList[comment.commentUid]}">
                                    <li class="list-group-item">
                                        <strong>${reply.username}</strong>: ${reply.content}
                                        <c:set var="isUserReply" value="${sessionScope.email eq reply.userEmail}" />
                                        <c:if test="${isUserReply}">
                                            <form action="comment/delete" method="post" style="display:inline;">
                                                <input type="hidden" name="commentUid" value="${reply.commentUid}"> <!-- replyUid를 전달 -->
                                                <button type="submit" class="btn btn-danger btn-sm ms-2">삭제</button>
                                            </form>
                                        </c:if>
                                        <div class="text-muted small">${reply.createAt}</div>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
