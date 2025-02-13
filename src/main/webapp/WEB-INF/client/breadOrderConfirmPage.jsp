<%@ page import="java.util.ArrayList" %>
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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/clientPageCommonStyle.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/breadOrderConfirmPageStyle.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/breadOrderConfirmPageScript.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>注文確認</title>
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
      <input type="text" id="customer-name" name="customerName" placeholder="お名前を入力してください" maxlength="64" required>
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
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<footer>
  &copy; Pan Shop
</footer>
</body>
</html>
