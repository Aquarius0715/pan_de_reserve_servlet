<%--
  Created by IntelliJ IDEA.
  User: ji1wxs
  Date: 2025/01/28
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>予約完了</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
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
      position: fixed;
      bottom: 0;
      background-color: #333;
      color: #fff;
      text-align: center;
      font-size: 0.9em;
    }

    .container {
      margin-top: 80px;
      width: 100%;
      max-width: 600px;
      background: #fff;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      padding: 20px;
      text-align: center;
    }

    h1 {
      font-size: 1.8em;
      margin-bottom: 20px;
      color: #333;
    }

    .visit-time {
      font-size: 1.2em;
      margin-bottom: 30px;
      color: #555;
    }

    .back-to-top {
      display: inline-block;
      padding: 10px 20px;
      font-size: 1em;
      color: #fff;
      background-color: #007bff;
      border: none;
      border-radius: 4px;
      text-decoration: none;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .back-to-top:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<header>
  パン屋の予約 - 予約完了
</header>

<div class="container">
  <h1>予約が完了しました！</h1>
  <p class="visit-time">来店時間: <strong id="visitTime"><%= request.getSession().getAttribute("receiveTime") %></strong></p>
  <a href="${pageContext.request.contextPath}/" class="back-to-top">トップに戻る</a>
</div>

<footer>
  &copy; Pan Shop
</footer>
</body>
</html>
