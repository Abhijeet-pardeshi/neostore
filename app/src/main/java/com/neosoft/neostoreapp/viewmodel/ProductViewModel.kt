package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.neosoft.neostoreapp.model.response.ProductResponse
import com.neosoft.neostoreapp.repository.ProductRepository

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    var mutableLiveData: MutableLiveData<ProductResponse>? = null
    private val productRepository = ProductRepository(application)

    fun getProductListResponse(): LiveData<ProductResponse>{
        return mutableLiveData as LiveData<ProductResponse>
    }

    init {
            mutableLiveData = MutableLiveData()
    }

    fun getProductList(productCategoryId: String){
        mutableLiveData?.value = mutableLiveData?.let {
            productRepository.getProductListResponse(productCategoryId,
                it
            ).value
        }
    }
}