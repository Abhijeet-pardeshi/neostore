package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class CartListResponseData(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("product_id")
    var productId: String? = null,

    @SerializedName("quantity")
    var quantity: String? = null,

    @SerializedName("product")
    var product: CartListProductResponse? = null,

    @SerializedName("count")
    var count: String? = null,

    @SerializedName("total")
    var total: String? = null

)