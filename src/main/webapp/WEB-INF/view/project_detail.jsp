<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2024-04-18
  Time: 오후 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Artfinity</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/project_detail.css">

</head>
<body>
<nav>
  <div class="logo">Art🖍finity</div>
  <div class="nav-items">
    <a href="/projects">프로젝트</a>
    <a href="/wallpapers">배경화면</a>
    <a href="/creators">크리에이터</a>
  </div>
  <div class="nav-right">
    <input type="search" placeholder="검색">
    <a href="/mypage"><img src="${pageContext.request.contextPath}/static/images/dog.png" alt="마이페이지"></a>
  </div>
</nav>

<div class="title_label">
  <a href="sample_login.html">< 뒤로가기</a>
  <p>제목이 들어가는 부분입니다.</p>
</div>

<div class="project-section">
  <div class="contents-container">
    <img class="project-img" src="${pageContext.request.contextPath}/static/images/project_img1.png" alt="Artwork">
    <p>코멘트가 들어가는 부분입니다.</p>
  </div>
  <div class="contents-container">
    <img class="project-img" src="${pageContext.request.contextPath}/static/images/project_img2.png" alt="Artwork">
    <p>코멘트가 들어가는 부분입니다.</p>
  </div>

  <div class="sticky-nav">
    <div class="sticky-nav-content">
      <a href="/mypage" class="sticky-nav-link-mypage"><img src="${pageContext.request.contextPath}/static/images/dog.png" alt="마이페이지"></a>
      <img class="sticky-nav-link heart" id="heartIcon" src="/static/icons/heart_empty_icon.png" alt="좋아요" onclick="toggleHeart(${user.userId})" style="cursor: pointer;">
      <a href="#comment-list-start"><img class="sticky-nav-link chat" src="${pageContext.request.contextPath}/static/icons/chat_icon.png" alt="댓글로 이동"></a>
    </div>
  </div>
</div>


<div class="detail">
  <div class="detail_content">
    <div class="icon-view">
      <img src="${pageContext.request.contextPath}/static/icons/view_icon.png" alt="">
      <span>100</span>
    </div>
    <div class="icon-heart">
      <img id="heartIcon_view" src="/static/icons/heart_empty_icon.png" alt="빈 좋아요">
      <span>100</span>
    </div>
    <div class="icon-comment">
      <img src="${pageContext.request.contextPath}/static/icons/chat_icon.png" alt="">
      <span>${commentCount}</span>
    </div>
  </div>
</div>

<div class="chat">
  <div id="comment-list-start" class="target-anchor"></div>
  <hr>

  <c:forEach items="${comments}" var="comment">
    <div class="chatBox" data-comment-id="${comment.commentId}">
      <div class="userImg"><img src="${pageContext.request.contextPath}/static/icons/userImg_basic_w.png" alt=""></div>

      <div class="userChat">
        <div class="userName">${comment.userName}</div>
        <div class="chatContent">${comment.content}</div>
      </div>

      <div class="chatSide">

          <%-- 현재 세션의 userId와 댓글의 userId 비교 --%>
        <c:if test="${comment.userId == sessionScope.userId}">
          <div class="chatEdit">
            <img src="${pageContext.request.contextPath}/static/icons/dot_icon.png" alt="">
            <div class="dropdown">
              <ul class="dropdown-menu">
                <li><a href="#edit" onclick="updateComment(${comment.commentId}); return false;">수정</a></li>
                <li><a href="#delete" onclick="deleteComment(${comment.commentId}); return false;">삭제</a></li>
              </ul>
            </div>
          </div>
        </c:if>

        <div class="chatDate">
          <fmt:formatDate value="${comment.created_at}" pattern="yyyy-MM-dd" />
        </div>

      </div>

    </div>
  </c:forEach>

  <%--<div class="chatBox">
    <div class="userImg"><img src="${pageContext.request.contextPath}/static/icons/userImg_basic_w.png" alt=""></div>
    <div class="userChat">
      <div class="userName">UserName</div>
      <div class="chatContent">댓글이 들어가는 부분입니다</div>
    </div>
    <div class="chatSide">

      <div class="chatEdit">
        <img src="${pageContext.request.contextPath}/static/icons/dot_icon.png" alt="">

        <div class="dropdown">
          <ul class="dropdown-menu">
            <li><a href="#edit">수정</a></li>
            <li><a href="#delete">삭제</a></li>
          </ul>
        </div>
      </div>

      <div class="chatDate">2024.04.16</div>
    </div>

  </div>--%>

  <hr>

  <%--댓글 입력하는 부분--%>
  <form action="/comment" method="post">
    <div class="chatBox">
      <div class="userImg"><img src="${pageContext.request.contextPath}/static/images/dog.png" alt=""></div>
      <div class="userChat">
        <div class="userName">${user.userName}</div>
        <div class="chatContent"><input name="content" type="text" value="" placeholder="댓글을 입력해 주세요"></div>
      </div>
      <div class="chatBtn">
        <input type="submit" name="register" value="등록" id="">
      </div>
    </div>
  </form>

</div>

<div class="footer"></div>

<script src="${pageContext.request.contextPath}/static/js/comment_dropdown.js"></script>
<script src="${pageContext.request.contextPath}/static/js/comment_btn.js"></script>
<script src="${pageContext.request.contextPath}/static/js/commentDelete.js"></script>
<script src="${pageContext.request.contextPath}/static/js/like_switch.js"></script>


</body>
</html>
