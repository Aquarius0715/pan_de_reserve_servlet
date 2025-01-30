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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>パン予約</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      height: 100vh;
      justify-content: flex-start;
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
      display: flex;
      flex-wrap: wrap;
      gap: 16px;
      padding: 16px;
      justify-content: center;
      margin-top: 70px;
      width: 100%;
      max-width: 1200px;
    }

    .bread-item {
      width: calc(25% - 16px);
      border: 1px solid #ddd;
      border-radius: 8px;
      overflow: hidden;
      background-color: #fff;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      position: relative;
      text-align: center;
      padding-bottom: 50px;
    }

    .bread-item img {
      width: 100%;
      height: 150px;
      object-fit: cover;
    }

    .bread-item .info {
      padding: 8px;
    }

    .bread-item .info h3 {
      margin: 8px 0;
      font-size: 16px;
    }

    .bread-item .info p.description {
      font-size: 14px;
      color: #666;
      margin: 4px 0 8px;
    }

    .bread-item .info p.price {
      margin: 8px 0;
      color: #555;
      font-weight: bold;
    }

    .quantity-control {
      position: absolute;
      bottom: 0;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #f8f8f8;
      padding: 8px 0;
      box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
    }

    .quantity-control button {
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 4px;
      padding: 4px 12px;
      cursor: pointer;
      font-size: 14px;
    }

    .quantity-control button:hover {
      background-color: #0056b3;
    }

    .quantity-control span {
      margin: 0 8px;
      font-size: 16px;
      font-weight: bold;
    }

    .total {
      width: 100%;
      text-align: center;
      margin-top: 20px;
      font-size: 1.2em;
      font-weight: bold;
    }

    .checkout {
      margin-top: 10px;
      padding: 10px 20px;
      background-color: #28a745;
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 1em;
      cursor: pointer;
    }

    .checkout:hover {
      background-color: #218838;
    }

    @media (max-width: 768px) {
      .bread-item {
        width: calc(50% - 16px);
      }
    }
  </style>
  <script>
    function updateQuantity(button, change) {
      const quantitySpan = button.parentNode.querySelector('.quantity');
      let quantity = parseInt(quantitySpan.textContent);

      quantity += change;
      if (quantity < 0) quantity = 0;
      if (quantity > 10) quantity = 10;
      quantitySpan.textContent = quantity;

      updateTotal();
    }

    function updateTotal() {
      const items = document.querySelectorAll('.bread-item');
      let total = 0;

      items.forEach(item => {
        const quantity = parseInt(item.querySelector('.quantity').textContent);
        const price = parseInt(item.querySelector('.info p.price').textContent.replace('¥', ''));
        total += quantity * price;
      });

      document.querySelector('.total').textContent = `合計金額: ¥` + total;
    }

    function submitOrder() {
      const form = document.getElementById('orderForm');
      const items = document.querySelectorAll('.bread-item');
      const orderData = [];

      items.forEach(item => {
        const breadId = item.getAttribute('data-id');
        const breadName = item.getAttribute('data-name');
        const breadPrice = item.getAttribute('data-price')
        const quantity = parseInt(item.querySelector('.quantity').textContent);

        if (quantity > 0) {
          orderData.push({ id: breadId, quantity, breadName, breadPrice });
        }
      });

      if (orderData.length === 0) {
        alert("注文内容が空です。");
        return;
      }

      const input = document.createElement('input');
      input.type = 'hidden';
      input.name = 'orderData';
      input.value = JSON.stringify(orderData);
      form.appendChild(input);

      form.submit();
    }
  </script>
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

<footer>
  &copy; Pan Shop
</footer>
</body>
</html>

