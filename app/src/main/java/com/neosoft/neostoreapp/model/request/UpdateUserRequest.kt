package com.neosoft.neostoreapp.model.request

class UpdateUserRequest constructor() {
    var accessToken: String? = null
    var email: String? = null
    var dob: String? = null
    var phoneNo: String? = null
    var profilePic: String? = null

    constructor(accessToken: String, email: String, dob: String, phoneNo: String, profilePic: String) : this() {
        this.accessToken = accessToken
        this.email = email
        this.dob = dob
        this.phoneNo = phoneNo
        this.profilePic = profilePic
    }
}