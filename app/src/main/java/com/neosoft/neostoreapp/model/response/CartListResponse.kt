package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class CartListResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("data")
    var cartListResponseData: ArrayList<CartListResponseData>? = null,

    @SerializedName("count")
    var count: String? = null,

    @SerializedName("total")
    var total: String? = null

)