<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/adminPageCommonStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/breadAppendPageStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/breadAppendPageScript.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン追加</title>
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
        <!-- タイトル -->
        <div class="title">
            <h2>パン追加</h2>
        </div>

        <!-- パン追加フォーム -->
        <form action="${pageContext.request.contextPath}/admin/breadAppend" method="post" enctype="multipart/form-data">
            <div>
                <label for="breadName">パン名称</label>
                <input type="text" id="breadName" name="breadName" placeholder="パンの名前を入力してください" maxlength="255" required>
            </div>

            <div>
                <label for="breadPrice">価格 (円)</label>
                <input type="number" id="breadPrice" name="breadPrice" placeholder="価格を入力してください" value="0" required>
            </div>

            <div>
                <label for="breadDescription">パン説明</label>
                <textarea id="breadDescription" name="breadDescription" rows="4" placeholder="パンの説明を入力してください"  maxlength="255" required></textarea>
            </div>

            <div>
                <label for="breadImage">画像</label>
                <input type="file" id="breadImage" name="breadImage" accept="image/*" required>
            </div>

            <div class="button-container" style="justify-content: flex-end;">
                <button type="submit" class="submit-button">追加</button>
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
