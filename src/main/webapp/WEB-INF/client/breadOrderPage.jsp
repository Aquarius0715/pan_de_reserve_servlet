<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderPageModel" %><%--
  Created by IntelliJ IDEA.
  User: ji1wxs
  Date: 2025/01/27
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/clientPageCommonStyle.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/breadOrderPageStyle.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/breadOrderPageScript.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>パン予約</title>
</head>
<body>
<header>
  パン屋の予約
</header>

<div class="container">
  <%
    ArrayList<BreadOrderPageModel> breadOrderPageModels = (ArrayList<BreadOrderPageModel>) request.getAttribute("breadOrderPageModels");
    for (BreadOrderPageModel breadOrderPageModel : breadOrderPageModels) {
  %>
  <div class="bread-item" data-id="<%= breadOrderPageModel.getId() %>" data-name="<%= breadOrderPageModel.getBreadName() %>" data-price="<%= breadOrderPageModel.getBreadPrice() %>">
    <img src="data:image/png;base64,<%= breadOrderPageModel.getBreadImage() %>" alt="パンの画像">
    <div class="info">
      <h3><%= breadOrderPageModel.getBreadName() %></h3>
      <p class="price">¥<%= breadOrderPageModel.getBreadPrice() %></p>
      <p class="description"><%= breadOrderPageModel.getBreadDescription() %></p>
    </div>
    <div class="quantity-control">
      <button onclick="updateQuantity(this, -1)">-</button>
      <span class="quantity">0</span>
      <button onclick="updateQuantity(this, 1)">+</button>
    </div>
  </div>
  <% } %>
  <div class="total">
    合計金額: ¥0
  </div>
</div>

<form id="orderForm" action="${pageContext.request.contextPath}/" method="POST">
  <button type="button" class="checkout" onclick="submitOrder()">確認画面に進む</button>
</form>
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->

<footer>
  &copy; Pan Shop
</footer>
</body>
</html>

