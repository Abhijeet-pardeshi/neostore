package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

class LoginErrorResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("user_msg")
    var userMessage: String? = null

)