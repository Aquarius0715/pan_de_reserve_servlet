<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveListPageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>予約一覧</title>
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
            margin: 20px auto 60px; /* Adjust for fixed header and footer */
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            table-layout: fixed;
        }

        table th, table td {
            border: 1px solid #ddd;
            text-align: left;
            padding: 10px;
            font-size: 1em;
            word-wrap: break-word;
        }

        table th:nth-child(1), table td:nth-child(1) {
            width: 5%; /* IDの列を狭くする */
        }

        table th:nth-child(2), table td:nth-child(2) {
            width: 25%; /* 予約時間の列を広くする */
        }

        table th {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        button {
            width: auto;
            padding: 8px 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
            justify-content: flex-start;
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
        <h2>予約一覧</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>予約時間</th>
                    <th>予約者氏名</th>
                    <th>予約詳細</th>
                    <th>お渡し済みにする</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<ReserveListPageModel> reserveList = (ArrayList<ReserveListPageModel>) request.getAttribute("reserveList");
                    int count = 1;
                    for (ReserveListPageModel reserveListPageModel : reserveList) {
                        if (reserveListPageModel.getReceiveStatus() == ReceiveStatus.RECEIVED) continue;
                %>
                <tr>
                    <td><%= count %></td>
                    <td><%= reserveListPageModel.getReserveTime() %></td>
                    <td><%= reserveListPageModel.getCustomerName() %></td>
                    <td><button onclick="location.href='${pageContext.request.contextPath}/admin/reserveList/reserveDetail?tableId=<%= count %>&id=<%= reserveListPageModel.getReserveId() %>'">予約詳細</button></td>
                    <td><button onclick="confirmDelivery('<%= reserveListPageModel.getReserveId() %>')">お渡し済にする</button></td>
                </tr>
                <%
                        count += 1;
                    }
                %>
            </tbody>
        </table>

        <h2>お渡し済み予約一覧</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>予約時間</th>
                    <th>予約者氏名</th>
                    <th>予約詳細</th>
                    <th>お渡し済み解除</th>
                </tr>
            </thead>
            <tbody>
            <%
                count = 1;
                for (ReserveListPageModel reserveListPageModel : reserveList) {
                    if (reserveListPageModel.getReceiveStatus() == ReceiveStatus.UNRECEIVED) continue;
            %>
                <tr>
                    <td><%= count %></td>
                    <td><%= reserveListPageModel.getReserveTime() %></td>
                    <td><%= reserveListPageModel.getCustomerName() %></td>
                    <td><button onclick="location.href='${pageContext.request.contextPath}/admin/reserveList/reserveDetail?tableId=<%= count %>&id=<%= reserveListPageModel.getReserveId() %>'">予約詳細</button></td>
                    <td><button onclick="cancelDelivery('<%= reserveListPageModel.getReserveId() %>')">お渡し済み解除</button></td>
                </tr>
            <%
                    count += 1;
                }
            %>
            </tbody>
        </table>
    </main>

    <footer>
        &copy; Pan Shop
    </footer>

    <script>
        function confirmDelivery(reservationId) {
            if (confirm('予約ID: ' + reservationId + ' をお渡し済みにしますか？')) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${pageContext.request.contextPath}/admin/reserveList/setToReceived';

                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'reserveId';
                input.value = reservationId;
                form.appendChild(input);

                document.body.appendChild(form);
                form.submit();
            }
        }

        function cancelDelivery(reservationId) {
            if (confirm('予約ID: ' + reservationId + ' のお渡し済みを解除しますか？')) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${pageContext.request.contextPath}/admin/reserveList/setToUnReceived';

                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'reserveId';
                input.value = reservationId;
                form.appendChild(input);

                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>
