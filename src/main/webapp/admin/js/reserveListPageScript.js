function confirmDelivery(reservationId, customerName) {
    if (confirm('予約者名: ' + customerName + ' 様のお渡し済みを解除しますか？')) {
        const form = document.createElement('form');
        form.method = 'POST';
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        form.action = `${contextPath}/reserveList/setToReceived`;

        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'reserveId';
        input.value = reservationId;
        form.appendChild(input);

        document.body.appendChild(form);
        form.submit();
    }
}

function cancelDelivery(reservationId, customerName) {
    if (confirm('予約者名: ' + customerName + ' 様のお渡し済みを解除しますか？')) {
        const form = document.createElement('form');
        form.method = 'POST';
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        form.action = `${contextPath}/reserveList/setToUnReceived`;

        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'reserveId';
        input.value = reservationId;
        form.appendChild(input);

        document.body.appendChild(form);
        form.submit();
    }
}