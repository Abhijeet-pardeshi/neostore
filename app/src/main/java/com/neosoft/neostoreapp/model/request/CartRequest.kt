package com.neosoft.neostoreapp.model.request

class CartRequest constructor() {
    var accessToken: String? = null
    var productId: String? = null
    var quantity: String? = null


    //request for either add to cart or edit cart
    constructor(
        accessToken: String,
        productId: String,
        quantity: String
    ) : this() {
        this.accessToken = accessToken
        this.productId = productId
        this.quantity = quantity
    }

    //request for delete cart item
    constructor(
        accessToken: String,
        productId: String
    ) : this() {
        this.accessToken = accessToken
        this.productId = productId
    }

    //request for listing cart items
    constructor(
        accessToken: String
    ) : this() {
        this.accessToken = accessToken
    }
}