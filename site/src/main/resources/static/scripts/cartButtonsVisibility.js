function hideNecessaryElement(productId) {
    var cart = getCart();
    var id = cart.indexOf(productId) === -1 ? "formRemove" : "formAdd";
    var elem = document.getElementById(id);
    if (elem != null) {
        elem.style.display = "none";
    }
}

function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}