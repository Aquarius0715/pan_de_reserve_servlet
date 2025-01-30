<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>パン追加</title>
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
            display: flex;
            justify-content: center;
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

        .title {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 40px;
        }

        label {
            font-size: 1em;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input, textarea {
            width: calc(100% - 20px);
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        textarea {
            resize: vertical;
        }

        .submit-button {
            padding: 10px 16px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
            text-align: center;
            align-self: flex-end;
        }

        .submit-button:hover {
            background-color: #218838;
        }
        .error-message {
            margin-top: 10px;
            color: red ;
            text-align: center;
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

    <footer>
        &copy; Pan Shop
    </footer>
</body>
</html>
