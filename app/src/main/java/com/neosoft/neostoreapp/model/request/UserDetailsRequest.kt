package com.neosoft.neostoreapp.model.request

class UserDetailsRequest constructor() {
    var accessToken: String? = null

    constructor(accessToken: String): this(){
        this.accessToken = accessToken
    }
}