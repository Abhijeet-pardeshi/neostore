package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.DetailRequest
import com.neosoft.neostoreapp.model.response.DetailResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository(application: Application) {
    private val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getDetailResponse(request: DetailRequest): MutableLiveData<DetailResponse> {
        val response = retrofitService.getDetail(request.productId!!)

        val mutableLiveData: MutableLiveData<DetailResponse> = MutableLiveData()
        response.enqueue(object : Callback<DetailResponse> {

            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
//                    Log.d("Detail S", response.body().toString())
                    mutableLiveData.value = response.body()
                } else {
                    mutableLiveData.value = null
                    Log.d("Detail U", "Not successful")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("Detail F", t.localizedMessage)
                mutableLiveData.value = null
            }

        })

        return mutableLiveData
    }
}