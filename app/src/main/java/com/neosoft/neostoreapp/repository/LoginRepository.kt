package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.neosoft.neostoreapp.model.request.LoginRequest
import com.neosoft.neostoreapp.model.response.LoginResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(application: Application) {
    private val retrofitService: ApiService = ApiClient.getRetrofitInstance().create(ApiService::class.java)
    val context = application.applicationContext!!

    fun getLoginResponse(request: LoginRequest): MutableLiveData<LoginResponse> {
        val response = retrofitService.loginUser(request.email.toString(), request.password.toString())

        val mutabledata: MutableLiveData<LoginResponse> = MutableLiveData()

        response.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Failure", t.printStackTrace().toString())
                mutabledata.value = null
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Log.e("Success", response.body().toString())
                    mutabledata.value = response.body()
                } else {
//                    val gson = GsonBuilder().create()
//                    val baseLoginResponse = gson.fromJson(response.errorBody()?.string(), LoginErrorResponse::class.java)

                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }
        })
        return mutabledata
    }
}