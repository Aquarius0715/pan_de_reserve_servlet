<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/loginPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/loginPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン予約管理 - ログイン</title>
</head>
<body>
<header>
    パン予約管理 Version 1.0.0
</header>

<div class="login-container">
    <h1>ログイン</h1>
    <form action="${pageContext.request.contextPath}/admin/login" method="POST">
        <label>
            <input type="text" name="username" placeholder="ユーザー名" maxlength="32" required>
        </label>
        <label>
            <input type="password" name="password" placeholder="パスワード" required>
        </label>
        <button type="submit">ログイン</button>
    </form>
    <div class="error-message">
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <% if (request.getSession().getAttribute("errorMessage") != null) { %>
        <p><%= request.getSession().getAttribute("errorMessage") %></p>
        <% } %>
    </div>
    <div class="message">
        <% if (request.getAttribute("message") != null) { %>
        <p><%= request.getAttribute("message") %></p>
        <% } %>
        <% if (request.getSession().getAttribute("message") != null) { %>
        <p><%= request.getSession().getAttribute("message") %></p>
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