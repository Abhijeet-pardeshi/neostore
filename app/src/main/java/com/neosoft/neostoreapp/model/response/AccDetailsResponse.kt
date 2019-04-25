package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class AccDetailsResponse(

    @SerializedName("status")
    var status: String? = null,

//    @SerializedName("total_carts")
//    var totalCarts: String? = null,
//
//    @SerializedName("total_orders")
//    var totalOrders: String? = null,

    @SerializedName("data")
    var data: AccDetailsResponseData? = null

//    @SerializedName("product_categories")
//    var productCategories: ArrayList<ProductCategoriesResponse>
)