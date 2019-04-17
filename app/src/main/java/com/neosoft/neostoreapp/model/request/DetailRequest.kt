package com.neosoft.neostoreapp.model.request

class DetailRequest constructor() {
    var productId: Int? = null

    constructor(
        productId: Int
    ) : this() {
        this.productId = productId
    }
}