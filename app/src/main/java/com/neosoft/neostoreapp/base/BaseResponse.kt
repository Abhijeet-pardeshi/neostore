package com.neosoft.neostoreapp.base

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("user_msg")
    var userMessage: String
)