package com.neosoft.neostoreapp.model.request

class ForgotPasswordRequest constructor() {
    var email: String? = null

    constructor(email: String) : this() {
        this.email = email
    }
}