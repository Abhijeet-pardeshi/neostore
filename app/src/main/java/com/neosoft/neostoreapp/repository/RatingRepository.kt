package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.facebook.stetho.inspector.protocol.module.Page
import com.neosoft.neostoreapp.model.request.SetRatingRequest
import com.neosoft.neostoreapp.model.response.RatingResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingRepository(application: Application) {
    val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getRatingResponse(request: SetRatingRequest, mutableLiveData: MutableLiveData<RatingResponse>){
        val response = retrofitService.setRating(request.productId.toString(), request.rating.toString())

        response.enqueue(object : Callback<RatingResponse> {

            override fun onResponse(call: Call<RatingResponse>, response: Response<RatingResponse>) {
                if (response.isSuccessful) {
                    Log.d("Rating S", response.body().toString())
                    mutableLiveData.value = response.body()
                } else {
                    Log.d("Rating U", response.message())
                    mutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<RatingResponse>, t: Throwable) {
                Log.d("Rating F", t.printStackTrace().toString())
                mutableLiveData.value = null
            }
        })
    }
}