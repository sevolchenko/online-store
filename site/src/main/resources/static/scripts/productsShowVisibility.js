function hideNecessaryElements(productId, photosCount) {
    var cart = getCart();
    var id = cart.indexOf(productId) === -1 ? "formRemove" : "formAdd";
    var elem = document.getElementById(id);
    if (elem != null) {
        elem.style.display = "none";
    }
    this.photosCount = photosCount;
    if (this.photosCount === 0) {
        currentPhotoIndex = -1;
    }
    showCurrentPhoto();
}

function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}

var currentPhotoIndex = 0;
var photosCount;

function showCurrentPhoto() {
    for (let i = 0; i < photosCount; i++) {
        var photo = document.getElementById("photo" + i);
        photo.style.display = "none";
    }
    var currPhoto = document.getElementById("photo" + currentPhotoIndex);
    currPhoto.style.display = "block";

    if (currentPhotoIndex !== -1) {
        if (currentPhotoIndex === 0) {
            document.getElementById("formPrevPhoto").style.display = "none";
        } else {
            document.getElementById("formPrevPhoto").style.display = "block";
        }

        if (currentPhotoIndex === photosCount - 1) {
            document.getElementById("formNextPhoto").style.display = "none";
        } else {
            document.getElementById("formNextPhoto").style.display = "block";
        }
    }
}

function prevPhoto() {
    if (currentPhotoIndex > 0) {
        currentPhotoIndex--;
        showCurrentPhoto();
    }
}

function nextPhoto() {
    if (currentPhotoIndex < photosCount - 1) {
        currentPhotoIndex++;
        showCurrentPhoto();
    }
}