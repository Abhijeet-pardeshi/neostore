package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.response.CartResponse
import com.neosoft.neostoreapp.repository.CartRepository

class DeleteCartViewModel(application: Application) : AndroidViewModel(application) {

    var mutableLiveData: MutableLiveData<CartResponse>? = null
    var cartRepository = CartRepository(application)

    fun getDeleteCartResponse(): LiveData<CartResponse> {
        return mutableLiveData as LiveData<CartResponse>
    }

    init {
        mutableLiveData = MutableLiveData()
    }

    fun deleteCart(request: CartRequest) {
        mutableLiveData?.let { cartRepository.getDeleteCartResponse(request, it) }
    }
}