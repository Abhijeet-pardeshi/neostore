package com.neosoft.neostoreapp.model.request

class LoginRequest constructor() {
    var email: String? = null
    var password: String? = null

    constructor(
        email: String,
        password: String
    ) : this() {
        this.email = email
        this.password = password
    }
}