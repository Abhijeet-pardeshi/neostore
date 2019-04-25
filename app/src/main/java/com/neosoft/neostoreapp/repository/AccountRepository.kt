package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.AccountRequest
import com.neosoft.neostoreapp.model.response.AccDetailsResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(application: Application) {
    private val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getAccountDetailsResponse(
        request: AccountRequest,
        mutableLiveData: MutableLiveData<AccDetailsResponse>
    ) {
        val response = request.accessToken?.let { retrofitService.getAccountDetails(it) }

        response?.enqueue(object : Callback<AccDetailsResponse> {

            override fun onResponse(call: Call<AccDetailsResponse>, response: Response<AccDetailsResponse>) {
                if (response.isSuccessful) {
                    Log.d("AccountRS", response.body().toString())
                    mutableLiveData.value = response.body()
                } else {
                    Log.d("AccountRU", response.errorBody().toString())
                    mutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<AccDetailsResponse>, t: Throwable) {
                Log.d("AccountRF", t.message)
                mutableLiveData.value = null
            }

        })
    }
}