<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadListPageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/breadListPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/breadListPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン管理画面</title>
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
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <footer>
        &copy; Pan Shop
    </footer>
</body>
</html>
