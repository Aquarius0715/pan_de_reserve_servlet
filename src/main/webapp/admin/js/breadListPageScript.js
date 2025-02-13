function confirmDelete(reservationId) {
    if (confirm('パンID: ' + reservationId + ' を削除しますか？')) {
        const form = document.createElement('form');
        form.method = 'POST';
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        form.action = `${contextPath}/admin/breadList/deleteBread`;

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