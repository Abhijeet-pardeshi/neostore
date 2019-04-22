package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.response.CartListResponse
import com.neosoft.neostoreapp.model.response.CartResponse
import com.neosoft.neostoreapp.repository.CartRepository

class ListCartViewModel(application: Application) : AndroidViewModel(application) {
    var mutableLiveData: MutableLiveData<CartListResponse>? = null
    var cartRepository = CartRepository(application)

    fun getCartListResponse(): LiveData<CartListResponse> {
        return mutableLiveData as LiveData<CartListResponse>
    }

    init {
        mutableLiveData = MutableLiveData()
    }

    fun getCartList(request: CartRequest) {
        cartRepository.getCartListResponse(request, mutableLiveData!!)
    }

}