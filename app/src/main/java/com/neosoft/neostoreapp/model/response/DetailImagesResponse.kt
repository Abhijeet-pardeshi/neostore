package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class DetailImagesResponse(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("product_id")
    var productId: Int? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null
)