package com.neosoft.neostoreapp.model.request

class RegisterRequest constructor() {
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var gender: String? = null
    var phoneNo: String? = null

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
        gender: String,
        phoneNo: String
    ) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.confirmPassword = confirmPassword
        this.gender = gender
        this.phoneNo = phoneNo
    }
}