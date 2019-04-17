package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.response.ProductResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(application: Application) {
    private val retrofitService: ApiService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getProductListResponse(productCategoryId: String, mutableLiveData: MutableLiveData<ProductResponse>): MutableLiveData<ProductResponse> {
        val response = retrofitService.getProducts(productCategoryId)
//        val mutableLiveData: MutableLiveData<ProductResponse> = MutableLiveData()
        response.enqueue(object : Callback<ProductResponse> {

            override fun onResponse(call: Call<ProductResponse>,response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    Log.d("Success",response.body().toString())
                    mutableLiveData.value = response.body()

                } else {
                    Log.d("Status", "Error")
                    mutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("Failure", t.localizedMessage)
                mutableLiveData.value = null
            }
        })

        return mutableLiveData
    }
}