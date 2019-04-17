package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class DetailResponseData(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("product_category_id")
    var productCategoryId: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("producer")
    var producer: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("cost")
    var cost: String? = null,

    @SerializedName("rating")
    var rating: String? = null,

    @SerializedName("view_count")
    var viewCount: String? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("product_images")
    var productImages: ArrayList<DetailImagesResponse>? = null
)