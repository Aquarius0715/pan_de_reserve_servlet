<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveDetailPageModel" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.Order" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>予約詳細</title>
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

        .button-container {
            width: 100%;
            background-color: #fff;
            padding: 10px 0;
            margin-top: 60px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .button-container nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center;
            gap: 10px;
            margin: 0;
        }

        .button-container nav ul li button {
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
        }

        .button-container nav ul li button:hover {
            background-color: #0056b3;
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

        main {
            flex: 1;
            width: 100%;
            max-width: 800px;
            margin: 20px auto 60px;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .back-button-container {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            margin-bottom: 10px;
        }

        .back-button {
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
        }

        .back-button:hover {
            background-color: #0056b3;
        }

        .title {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            border: 1px solid #ddd;
            text-align: left;
            padding: 10px;
        }

        table th {
            background-color: #f2f2f2;
        }
    </style>
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

<footer>
    &copy; Pan Shop
</footer>
</body>
</html>