package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class CartListProductResponse(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("product_category")
    var productCategory: String? = null,

    @SerializedName("cost")
    var cost: String? = null,

    @SerializedName("product_images")
    var productImages: String? = null,

    @SerializedName("sub_total")
    var subTotal: String? = null

)