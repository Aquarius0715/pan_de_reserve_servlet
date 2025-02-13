<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveDetailPageModel" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.Order" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/reserveDetailPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/reserveDetailPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>予約詳細</title>
</head>
<body>
<header>
    パン予約管理 Version 1.0.0
</header>

<div class="button-container">
    <nav>
        <ul>
            <li><button onclick="location.href='${pageContext.request.contextPath}/admin/reserveList'">予約一覧</button></li>
            <li><button onclick="location.href='${pageContext.request.contextPath}/admin/breadList'">パン一覧</button></li>
            <li><button onclick="location.href='${pageContext.request.contextPath}/admin/breadAppend'">パン追加</button></li>
        </ul>
    </nav>
</div>

<main>
    <!-- 戻るボタン -->
    <div class="back-button-container">
        <button class="back-button" onclick="location.href='${pageContext.request.contextPath}/admin/reserveList'">戻る</button>
    </div>

    <!-- タイトル -->
    <div class="title">
        <h2>予約詳細</h2>
    </div>

    <!-- 予約者情報テーブル -->
    <table>
        <thead>
        <tr>
            <th colspan="2">予約者情報</th>
        </tr>
        </thead>
        <tbody>
        <%
            ReserveDetailPageModel reserveDetailPageModel = (ReserveDetailPageModel) request.getAttribute("reserveDetailPageModel");
            String tableId = (String) request.getAttribute("tableId");
        %>
        <tr>
            <th>ID</th>
            <td><%= tableId %></td>
        </tr>
        <tr>
            <th>氏名</th>
            <td><%= reserveDetailPageModel.getCustomerName() %></td>
        </tr>
        <tr>
            <th>電話番号</th>
            <td><%= reserveDetailPageModel.getCustomerPhoneNumber() %></td>
        </tr>
        <tr>
            <th>予約時間</th>
            <td><%= reserveDetailPageModel.getReceiveTime() %></td>
        </tr>
        </tbody>
    </table>

    <!-- 予約内容テーブル -->
    <table>
        <thead>
        <tr>
            <th colspan="2">予約内容</th>
        </tr>
        <tr>
            <th>商品名</th>
            <th>数量</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Order order : reserveDetailPageModel.getOrders()) {
        %>
        <tr>
            <td><%= order.getBreadName() %></td>
            <td><%= order.getQuantity() %>個</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</main>
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
<footer>
    &copy; Pan Shop
</footer>
</body>
</html>