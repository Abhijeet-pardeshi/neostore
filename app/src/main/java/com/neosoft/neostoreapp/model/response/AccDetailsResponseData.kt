package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class AccDetailsResponseData(

    @SerializedName("user_data")
    var userDataResponse: UserDataResponse? = null,

    @SerializedName("product_categories")
    var productCategoriesResponse: ArrayList<ProductCategoriesResponse>? = null,

    @SerializedName("total_carts")
    var totalCarts: String? = null,

    @SerializedName("total_orders")
    var totalOrders: String? = null

)