package com.neosoft.neostoreapp.model.request

class SetRatingRequest constructor() {
    var productId: String? = null
    var rating: String? = null

    constructor(
        productId: String,
        rating: String
    ) : this() {
        this.productId = productId
        this.rating = rating
    }

}