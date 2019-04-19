package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.neosoft.neostoreapp.model.response.CartResponse

class CartViewModel(application: Application): AndroidViewModel(application){
    var mutableLiveData: MutableLiveData<CartResponse>? = null


}