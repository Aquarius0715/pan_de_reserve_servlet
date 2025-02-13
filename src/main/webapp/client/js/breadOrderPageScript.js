function updateQuantity(button, change) {
    const quantitySpan = button.parentNode.querySelector('.quantity');
    let quantity = parseInt(quantitySpan.textContent);

    quantity += change;
    if (quantity < 0) quantity = 0;
    if (quantity > 10) quantity = 10;
    quantitySpan.textContent = quantity;

    updateTotal();
}

function updateTotal() {
    const items = document.querySelectorAll('.bread-item');
    let total = 0;

    items.forEach(item => {
        const quantity = parseInt(item.querySelector('.quantity').textContent);
        const price = parseInt(item.querySelector('.info p.price').textContent.replace('¥', ''));
        total += quantity * price;
    });

    document.querySelector('.total').textContent = `合計金額: ¥` + total;
}

function submitOrder() {
    const form = document.getElementById('orderForm');
    const items = document.querySelectorAll('.bread-item');
    const orderData = [];

    items.forEach(item => {
        const breadId = item.getAttribute('data-id');
        const breadName = item.getAttribute('data-name');
        const breadPrice = item.getAttribute('data-price')
        const quantity = parseInt(item.querySelector('.quantity').textContent);

        if (quantity > 0) {
            orderData.push({ id: breadId, quantity, breadName, breadPrice });
        }
    });

    if (orderData.length === 0) {
        alert("注文内容が空です。");
        return;
    }

    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'orderData';
    input.value = JSON.stringify(orderData);
    form.appendChild(input);

    form.submit();
}