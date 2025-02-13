<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadDetailPageModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/breadDetailPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/breadDetailPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン詳細</title>
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
            <button class="back-button" onclick="location.href='${pageContext.request.contextPath}/admin/breadList'">戻る</button>
        </div>

        <!-- タイトル -->
        <div class="title">
            <h2>パン詳細</h2>
        </div>

        <!-- パン詳細フォーム -->
        <%
            BreadDetailPageModel breadDetailPageModel = (BreadDetailPageModel) request.getAttribute("breadDetailPageModel");
        %>
        <form action="${pageContext.request.contextPath}/admin/breadList/breadDetail" method="post">
            <div>
                <img src="data:image/png;base64,<%= breadDetailPageModel.getBreadImage() %>" alt="パンの画像" class="bread-image">
            </div>

            <div>
                <label for="breadName">パン名称</label>
                <input type="text" id="breadName" name="breadName" maxlength="255" value=<%= breadDetailPageModel.getBreadName() %>>
            </div>

            <div>
                <input type="hidden" id="breadId" name="breadId" value=<%= breadDetailPageModel.getId() %>>
            </div>

            <div>
                <label for="breadPrice">価格 (円)</label>
                <input type="number" id="breadPrice" name="breadPrice" value=<%= breadDetailPageModel.getBreadPrice() %>>
            </div>

            <div>
                <label for="breadDescription">パン説明</label>
                <textarea id="breadDescription" name="breadDescription" rows="4" maxlength="255"><%= breadDetailPageModel.getBreadDescription() %></textarea>
            </div>

            <div>
                <input type="hidden" id="currentBreadImage" name="currentBreadImage" value=<%= breadDetailPageModel.getBreadImage() %>>
            </div>

            <div class="button-container">
                <button type="submit" class="submit-button">変更</button>
            </div>
        </form>
        <div class="error-message">
            <% if (request.getAttribute("errorMessage") != null) { %>
            <p><%= request.getAttribute("errorMessage") %></p>
            <% } %>
        </div>
    </main>
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <div class="invisible-space">&nbsp;</div> <!-- 見えないスペース -->
    <footer>
        &copy; Pan Shop
    </footer>
</body>
</html>
