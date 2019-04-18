package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("result")
    var result: Boolean? = null,

    @SerializedName("total_carts")
    var totalCarts: Int? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("user_msg")
    var userMessage: String? = null

)