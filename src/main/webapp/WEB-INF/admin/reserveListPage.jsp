<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveListPageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/reserveListPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/reserveListPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>予約一覧</title>
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
                    <td><button onclick="confirmDelivery('<%= reserveListPageModel.getReserveId() %>', '<%= reserveListPageModel.getCustomerName() %>')">お渡し済にする</button></td>
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
                    <td><button onclick="cancelDelivery('<%= reserveListPageModel.getReserveId() %>', '<%= reserveListPageModel.getCustomerName() %>')">お渡し済み解除</button></td>
                </tr>
            <%
                    count += 1;
                }
            %>
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
