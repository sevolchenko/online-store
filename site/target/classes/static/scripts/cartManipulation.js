if (localStorage.getItem('cart') == null) {
    var cart = [];
    localStorage.setItem('cart', JSON.stringify(cart));
}

function addProductToCart(productId) {
    var cart = getCart();
    if (cart.indexOf(productId) === -1) {
        cart.push(productId);
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    window.location.reload();
}

function removeProductFromCart(productId) {
    var cart = getCart();
    var filtered = cart.filter(function(currProductId){
        return productId !== currProductId;
    });
    localStorage.setItem('cart', JSON.stringify(filtered));
    window.location.reload();
}

function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}