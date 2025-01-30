<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadListPageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadAppendPageModel" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン管理画面</title>
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

        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 16px;
            padding: 16px;
            justify-content: flex-start;
            margin-top: 10px;
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

        .bread-item .info p {
            margin: 4px 0;
            color: #555;
        }

        .bread-item .status {
            margin: 8px 0;
            font-size: 14px;
            font-weight: bold;
            color: green;
        }

        .bread-item .status-sold-out {
            margin: 8px 0;
            font-size: 14px;
            font-weight: bold;
            color: red;
        }

        .bread-item .actions {
            display: flex;
            justify-content: space-between;
            gap: 8px;
            padding: 8px;
        }

        .bread-item .actions button {
            flex: 1;
            padding: 10px;
            border: none; /* 縁取りを削除 */
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            height: 40px; /* ボタンの高さを統一 */
            line-height: 1.2;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 軽い影で立体感を追加 */
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .details-btn {
            background-color: #4CAF50;
            color: white;
        }

        .delete-btn {
            background-color: #f44336;
            color: white;
        }

        .toggle-btn {
            background-color: #87CEEB;
            color: white;
        }

        .details-btn:hover {
            background-color: #45a049;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15); /* ホバー時の影を強調 */
        }

        .delete-btn:hover {
            background-color: #d32f2f;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
        }

        .toggle-btn:hover {
            background-color: #6ca6cd;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
        }
    </style>
    <script>
        function confirmDelete(reservationId) {
            if (confirm('パンID: ' + reservationId + ' を削除しますか？\n現在予約されているパンデータも消えてしまいます。')) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${pageContext.request.contextPath}/admin/breadList/deleteBread';

                // 必要なデータをフォームに追加
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'id';
                input.value = reservationId;
                form.appendChild(input);

                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
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

    <div class="container">
        <%
            ArrayList<BreadListPageModel> breadListPageModels = (ArrayList<BreadListPageModel>) request.getAttribute("breadListPageModels");
            for (BreadListPageModel breadListPageModel : breadListPageModels) {
        %>
        <div class="bread-item">
            <img src="data:image/png;base64,<%= breadListPageModel.getBreadImage() %>" alt="パンの画像">
            <div class="info">
                <h3><%= breadListPageModel.getBreadName() %></h3>
                <p>¥<%= breadListPageModel.getBreadPrice() %></p>
                <% if (breadListPageModel.getSalesStatus() == SalesStatus.AVAILABLE) { %>
                    <div class="status">販売中</div>
                <%} else {%>
                    <div class="status-sold-out">売り切れ</div>
                <%}%>
            </div>
            <div class="actions">
                <button class="details-btn" onclick="location.href='${pageContext.request.contextPath}/admin/breadList/breadDetail?id=<%= breadListPageModel.getId() %>'">詳細確認</button>
                <button class="delete-btn" onclick="confirmDelete(<%= breadListPageModel.getId() %>)">削除</button>
                <form method="POST" action="${pageContext.request.contextPath}/admin/breadList/toggleStatus">
                    <input type="hidden" name="id" value="<%= breadListPageModel.getId() %>">
                    <input type="hidden" name="salesStatus" value="<%= breadListPageModel.getSalesStatus().name() %>">
                    <button type="submit" class="toggle-btn">販売切替</button>
                </form>
            </div>
        </div>
        <% } %>
    </div>

    <footer>
        &copy; Pan Shop
    </footer>
</body>
</html>
