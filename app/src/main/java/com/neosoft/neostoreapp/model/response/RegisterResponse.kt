package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName
import com.neosoft.neostoreapp.model.response.RegisterResponseData

data class RegisterResponse(

    @SerializedName("status")
    var status: Int? = null,

    @SerializedName("data")
    var data: RegisterResponseData? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("user_msg")
    var userMessage: String? = null
)