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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/clientPageCommonStyle.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/breadOrderCompletePageStyle.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/breadOrderCompletePageScript.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>予約完了</title>
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
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<footer>
  &copy; Pan Shop
</footer>
</body>
</html>
