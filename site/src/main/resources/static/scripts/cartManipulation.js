if (localStorage.getItem('cart') == null) {
    var cart = [];
    localStorage.setItem('cart', JSON.stringify(cart));
}

function addProductToCart(productId) {
    var cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push(productId)
    localStorage.setItem('cart', JSON.stringify(cart))
}

function removeProductFromCart(productId) {
    var cart = JSON.parse(localStorage.getItem('cart')) || [];
    var filtered = cart.filter(function(currProductId){
        return productId !== currProductId;
    });
    localStorage.setItem('cart', JSON.stringify(filtered))
}

function containsInCart(productId) {

}