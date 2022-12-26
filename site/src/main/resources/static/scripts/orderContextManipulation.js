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
            let rowContext = {}
            rowContext['id'] = row.id;
            rowContext['count'] = Object.values(document.getElementsByName("count")).find(el => el.id === row.id).value;
            rowContext['size'] = Object.values(document.getElementsByName("size")).find(el => el.id === row.id).value;
            //orderContext[i] = rowContext;
        }
    }
    localStorage.setItem('orderContext', JSON.stringify(orderContext));
    console.log(localStorage.getItem('orderContext'))

    location.href="../orders/new";
}

function sendOrderContext() {
    document.getElementById('approve').submit();

    var cart = localStorage.getItem('orderContext')

    fetch('http://localhost:8080/orders/addCartInfo', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: cart
    })

    document.location.href = "./success"

    localStorage.removeItem('cart')
    localStorage.removeItem('orderContext')
}