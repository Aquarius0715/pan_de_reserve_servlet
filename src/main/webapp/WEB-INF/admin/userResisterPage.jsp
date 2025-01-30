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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン予約管理 - ユーザー登録</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
            justify-content: center;
            align-items: center;
            background-color: #f8f8f8;
        }

        header {
            width: 100%;
            padding: 10px;
            background-color: #333;
            color: #fff;
            text-align: center;
            font-size: 1.5em;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        footer {
            width: 100%;
            padding: 10px;
            position: absolute;
            bottom: 0;
            background-color: #333;
            color: #fff;
            text-align: center;
            font-size: 0.9em;
        }

        .register-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-top: 80px;
        }

        .register-container h1 {
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
        }

        .register-container button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
        }

        .register-container button:hover {
            background-color: #218838;
        }
        .error-message {
            margin-top: 10px;
            color: red ;
            text-align: center;
        }

        .message {
            margin-top: 10px;
            color: green ;
            text-align: center;
        }
    </style>
</head>
<body>
<header>
    パン予約管理 Version 1.0.0
</header>

<div class="register-container">
    <h1>ユーザー登録</h1>
    <form action="${pageContext.request.contextPath}/admin/userResister" method="post">
        <label>
            <input type="text" name="username" placeholder="ユーザー名" required>
        </label>
        <label>
            <input type="password" name="password" placeholder="パスワード" required>
        </label>
        <label>
            <input type="password" name="confirmPassword" placeholder="パスワード確認" required>
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

<footer>
    &copy; Pan Shop
</footer>
</body>
</html>
