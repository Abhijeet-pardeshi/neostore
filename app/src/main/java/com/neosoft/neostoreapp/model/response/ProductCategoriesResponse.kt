package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class ProductCategoriesResponse(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("icon_image")
    var iconImage: String? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null
)