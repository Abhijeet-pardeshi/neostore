package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("user_msg")
    var userMessage: String,

    @SerializedName("data")
    var data: LoginResponseData
)