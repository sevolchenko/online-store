function buildOrderContext() {

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

    return orderContext;
}

async function sendOrder() {

    var orderDetails = {};
    new FormData(document.getElementById("orderForm"))
        .forEach((value, key) => orderDetails[key] = value);


    var orderContext = buildOrderContext();

    var order = {};
    order['orderDetails'] = orderDetails;
    order['orderContext'] = orderContext;

    try {
        const response = await fetch('http://localhost:8080/cart/addCartInfo', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });

        if (!response.ok) {
            const msg = response.status;
            throw new Error(msg);
        }

        const data = await response.json();

        if (data["orderId"] !== undefined) {
            for (var prod in orderContext) {
                removeProductFromCart(parseInt(orderContext[prod]["id"]));
            }

            location.href = "./orders/" + data["orderId"];

        } else {
            let elements = document.getElementsByClassName("errorMsg");
            Array.prototype.forEach.call(elements, function(el) {
                el.style.display = "none";
            });
            let errors = data["errors"];

            for (var key in errors) {
                var div = document.getElementById(key + "Error");
                div.innerText = errors[key];
                div.style.display = "block";
            }
        }

    } catch (error) {
        console.log(error);
    }
}

function removeProductFromCart(productId) {
    var cart = getCart();
    var filtered = cart.filter(function(currProductId){
        return productId !== currProductId;
    });
    localStorage.setItem('cart', JSON.stringify(filtered));
}

function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}