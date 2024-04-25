<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2024-04-19
  Time: 오후 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity_signup</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signup.css">

</head>
<body>
<nav>
    <div class="logo">Art🖍️finity</div>
</nav>
<div class="content-box">
    <div class="title">회원가입</div>
    <form id="signup-form" action="" method="post">
        <input type="text" id="name" name="userName" placeholder="NAME">
        <span id="name-error" class="name-message"></span><br>
        <input type="email" id="email" name="userEmail" placeholder="EMAIL">
        <span id="email-error" class="error-message"></span><br>
        <input type="password" id="password" name="userPassword" placeholder="PASSWORD">
        <span id="password-error" class="error-message"></span><br>
        <input type="password" id="password-check" name="password-check" placeholder="PASSWORD 확인">
        <span id="password-check-error" class="error-message"></span><br>
        <input type="tel" name="userPhone" placeholder="PHONE" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}">
        <span id="phone-error" class="error-message"></span><br>
        <input type="submit" class="signup-btn" value="회원가입">
    </form>
</div>
<script src="${pageContext.request.contextPath}/static/js/signup.js"></script>
</body>

</html>
