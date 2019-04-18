package com.neosoft.neostoreapp.model.request

class OrderRequest constructor() {
    var accessToken: String? = null
    var address: String? = null
    var orderId: Int? = null

    constructor(accessToken: String) : this() {
        this.accessToken = accessToken
    }

    constructor(accessToken: String, address: String) : this() {
        this.accessToken = accessToken
        this.address = address
    }

    constructor(accessToken: String, orderId: Int) : this() {
        this.accessToken = accessToken
        this.orderId = orderId
    }
}