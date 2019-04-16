package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.neosoft.neostoreapp.model.response.RegisterResponse
import com.neosoft.neostoreapp.model.request.RegisterRequest
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.RegisterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository(application: Application) {
    private var retrofitClient: RegisterService = ApiClient.getRetrofitInstance().create(RegisterService::class.java)
    val context = application.applicationContext!!

    fun getRegResponse(request: RegisterRequest): MutableLiveData<RegisterResponse> {
        val response = retrofitClient.registerUser(
            request.firstName.toString(),
            request.lastName.toString(),
            request.email.toString(),
            request.password.toString(),
            request.confirmPassword.toString(),
            request.gender.toString(),
            request.phoneNo!!
        )

        val data: MutableLiveData<RegisterResponse> = MutableLiveData()

        response.enqueue(object : Callback<RegisterResponse> {

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {

                if (response.isSuccessful) {
                    data.value = response.body()
                    Toast.makeText(context, response.body()?.userMessage, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
//                        Log.e("Url",call.request().url().toString())
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("Failure", t.printStackTrace().toString())
                data.value = null
            }
        })
        return data
    }
}

//    fun getRegResponse(request: RegisterRequest): MutableLiveData<RegisterResponse>{
//        val registerResponseAsyncTask = RegisterResponseAsyncTask(retrofitClient)
//        return registerResponseAsyncTask.execute(request).get()
//    }
//
//    companion object {
//        class RegisterResponseAsyncTask(var retrofitClient: RegisterService): AsyncTask<RegisterRequest, Unit, MutableLiveData<RegisterResponse>>() {
//            override fun doInBackground(vararg params: RegisterRequest?): MutableLiveData<RegisterResponse> {
//                val request = params[0]
//                val response = retrofitClient.registerUser(
//                    request?.firstName.toString(),
//                    request?.lastName.toString(),
//                    request?.email.toString(),
//                    request?.password.toString(),
//                    request?.confirmPassword.toString(),
//                    request?.gender.toString(),
//                    request?.phoneNo!!
//                )
//
//                val data: MutableLiveData<RegisterResponse> = MutableLiveData()
//
//                response.enqueue(object: Callback<RegisterResponse> {
//
//                    override fun onResponse(call: Call<RegisterResponse>, response: BaseResponse<RegisterResponse>) {
//
//
//
//                        response.body().toString()
//                        data.value=response.body()
//                        if(response.isSuccessful){
//
//                            Log.e("onResponse",response.body().toString())
//                            Log.e("StatusCode",response.code().toString())
//                             Log.e("JSONStatusCode",(response.body() as RegisterResponse).status.toString())
//
//                        }else{
//                            Log.e("onResponse",response.body().toString())
//                            Log.e("StatusCode",response.code().toString())
//                             Log.e("JSONStatusErrr",Gson().toJson(response.errorBody().toString()))
//
//                        }
//
//                        Log.e("Url",call.request().url().toString())
//                    }
//
//                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                        Log.e("Failure",t.printStackTrace().toString())
//                        data.value = null
//                    }
//
//                })
//                return data
//            }
//
//        }
//    }

//                        if(response.isSuccessful){
//
//                            Log.e("onResponse",response.body().toString())
//                            Log.e("StatusCode",response.code().toString())
//                             Log.e("JSONStatusCode",(response.body() as RegisterResponse).status.toString())
//
//                        }else{
//                            Log.e("onResponse",response.errorBody().toString())
//                            Log.e("StatusCode",response.code().toString())
//                             Log.e("JSONStatusErrr",Gson().toJson(response.errorBody().toString()))
//
//                        }