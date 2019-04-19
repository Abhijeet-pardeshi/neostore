package com.neosoft.neostoreapp.repository

import android.app.Application
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService

class CartRepository(application: Application) {
    private val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getCartResponse(request: CartRequest) {

    }
}