package com.neosoft.neostoreapp.model

data class CartListItem(
    var cartItemId: String? = null,
    var cartItemImage: String? = null,
    var cartItemName: String? = null,
    var cartItemCategory: String? = null,
    var cartItemQuantity: String? = null,
    var cartItemTotal: String? = null
)