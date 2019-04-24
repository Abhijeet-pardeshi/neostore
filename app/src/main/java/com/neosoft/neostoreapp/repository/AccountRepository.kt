package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.AccountRequest
import com.neosoft.neostoreapp.model.response.AccountDetailsResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(application: Application) {
    private val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getAccountDetailsResponse(
        request: AccountRequest,
        mutableLiveData: MutableLiveData<AccountDetailsResponse>
    ) {
        val response = request.accessToken?.let { retrofitService.getAccountDetails(it) }

        response?.enqueue(object : Callback<AccountDetailsResponse> {

            override fun onResponse(call: Call<AccountDetailsResponse>, response: Response<AccountDetailsResponse>) {
                if (response.isSuccessful) {
                    Log.d("AccountRS", response.body().toString())
                } else {
                    Log.d("AccountRU", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<AccountDetailsResponse>, t: Throwable) {
                Log.d("AccountRF", t.message)
            }

        })
    }
}