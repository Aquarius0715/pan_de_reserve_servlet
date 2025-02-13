<%--
  Created by IntelliJ IDEA.
  User: ji1wxs
  Date: 2025/01/27
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/userResisterPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/userResisterPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン予約管理 - ユーザー登録</title>
</head>
<body>
<header>
    パン予約管理 Version 1.0.0
</header>

<div class="register-container">
    <h1>ユーザー登録</h1>
    <form action="${pageContext.request.contextPath}/admin/userResister" method="post">
        <label>
            <input type="text" name="username" placeholder="ユーザー名" maxlength="32" required>
        </label>
        <label>
            <input type="password" name="password" placeholder="パスワード" minlength="8" required>
        </label>
        <label>
            <input type="password" name="confirmPassword" placeholder="パスワード確認" minlength="8" required>
        </label>
        <button type="submit">登録</button>
    </form>
    <div class="error-message">
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p><%= request.getAttribute("errorMessage") %></p>
        <% } %>
    </div>
    <div class="message">
        <% if (request.getAttribute("message") != null) { %>
        <p><%= request.getAttribute("message") %></p>
        <% } %>
    </div>
</div>
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<footer>
    &copy; Pan Shop
</footer>
</body>
</html>
