package com.neosoft.neostoreapp.model.request

class ChangePasswordRequest constructor(){
    var accessToken: String? = null
    var oldPassword: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    constructor(accessToken: String, oldPassword: String, password: String, confirmPassword: String) : this() {
        this.accessToken = accessToken
        this.oldPassword = oldPassword
        this.password = password
        this.confirmPassword = confirmPassword
    }
}