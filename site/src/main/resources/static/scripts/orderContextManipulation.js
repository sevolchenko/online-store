function saveOrderContext() {

    var table = document.getElementById("tableCart");

    var orderContext = [];

    for (var i = 0, row; row = table.rows[i]; i++) {
        var checkBox = Object.values(document.getElementsByName("selection")).find((el) => el.id === row.id);
        if (checkBox.checked) {
            orderContext[i] = {
                id: row.id,
                count: Object.values(document.getElementsByName("count")).find(el => el.id === row.id).value,
                size: Object.values(document.getElementsByName("size")).find(el => el.id === row.id).value
            }
        }
    }
    localStorage.setItem('orderContext', JSON.stringify(orderContext));
    console.log(localStorage.getItem('orderContext'))

    location.href="../orders/new";
}

function sendOrderContext() {
    document.getElementById('approve').submit();

    var orderContext = localStorage.getItem('orderContext')

    fetch('http://localhost:8080/orders/addCartInfo', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: orderContext
    })

    document.location.href = "./success";

    localStorage.removeItem('cart');
    localStorage.removeItem('orderContext');
}


function sendOrder() {
    var orderDetails = {};
    new FormData(document.getElementById("orderForm"))
        .forEach((value, key) => orderDetails[key] = value);
    var jsonObject = JSON.stringify(orderDetails);

    console.log(jsonObject);

    var orderContext = localStorage.getItem('orderContext');

    var order = {};
    order['orderDetails'] = orderDetails;
    order['orderContext'] = orderContext;

    fetch('http://localhost:8080/cart/addCartInfo', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: order
    })

    document.location.href = "./success";

    localStorage.removeItem('cart');
    localStorage.removeItem('orderContext');
}