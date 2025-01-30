<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.facade.impl.BreadOrderPageFacadeImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderConfirmPageModel" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.Order" %><%--
  Created by IntelliJ IDEA.
  User: ji1wxs
  Date: 2025/01/28
  Time: 5:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>注文確認</title>
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
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 8px;
      font-size: 1em;
      font-weight: bold;
    }

    .form-group input, .form-group select {
      width: 100%;
      padding: 10px;
      font-size: 1em;
      border: 1px solid #ddd;
      border-radius: 4px;
    }

    .form-group input:focus, .form-group select:focus {
      outline: none;
      border-color: #007bff;
    }

    .actions {
      margin-top: 30px;
      display: flex;
      justify-content: space-between;
    }

    .actions button {
      padding: 10px 20px;
      font-size: 1em;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .actions .back-btn {
      background-color: #f44336;
      color: #fff;
    }

    .actions .back-btn:hover {
      background-color: #d32f2f;
    }

    .actions .confirm-btn {
      background-color: #28a745;
      color: #fff;
    }

    .actions .confirm-btn:hover {
      background-color: #218838;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }
    thead th {
      text-align: left;
      border-bottom: 1px solid #ddd;
      padding: 8px;
    }
    tbody td {
      padding: 8px;
      border-bottom: 1px solid #ddd;
    }
    tbody td:nth-child(2) {
      text-align: center;
    }
    tbody td:nth-child(3) {
      text-align: right;
    }
    .total {
      margin-top: 20px;
      text-align: center;
      font-size: 1.2em;
      font-weight: bold;
      color: #333;
    }
  </style>
</head>
<body>
<header>
  パン屋の予約 - 注文確認
</header>

<div class="container">
  <form action="${pageContext.request.contextPath}/orderConfirm" method="POST">
    <!-- 名前入力フォーム -->
    <div class="form-group">
      <label for="customer-name">お名前</label>
      <input type="text" id="customer-name" name="customerName" placeholder="お名前を入力してください" required>
    </div>

    <div class="form-group">
      <label for="phone-number">携帯電話番号</label>
      <input type="tel" id="phone-number" name="phoneNumber" placeholder="09012345678"
             pattern="^0\d{1,4}\d{1,4}\d{4}$" required maxlength="11">
      <small>※ハイフンを含めない形式で入力してください</small>
    </div>

    <!-- 来店日時入力フォーム -->
    <div class="form-group">
      <label for="visit-date">来店日時</label>
      <input type="datetime-local" id="visit-date" name="visitDate" required>
    </div>

    <!-- 注文内容 -->
    <div class="order-summary">
      <h3>注文内容</h3>
      <table>
        <thead>
        <tr>
          <th>パンの名前</th>
          <th>数量</th>
          <th>価格</th>
        </tr>
        </thead>
        <tbody>
        <%
          ArrayList<Order> orders = (ArrayList<Order>) request.getSession().getAttribute("orders");
          int totalPrice = 0;
          for (Order order : orders) {
            totalPrice += order.getBreadPrice() * order.getQuantity();
        %>
        <tr>
          <td><%= order.getBreadName() %></td>
          <td><%= order.getQuantity() %>個</td>
          <td>¥<%= order.getBreadPrice() %></td>
        </tr>
        <% } %>
        </tbody>
      </table>
      <!-- 合計金額 -->
      <div class="total">合計金額: ¥<%= totalPrice %></div>
    </div>

    <!-- ボタン -->
    <div class="actions">
      <button type="button" class="back-btn" onclick="location.href='${pageContext.request.contextPath}/'">戻る</button>
      <button type="submit" class="confirm-btn">確定する</button>
    </div>
  </form>
</div>

<footer>
  &copy; Pan Shop
</footer>
</body>
</html>
