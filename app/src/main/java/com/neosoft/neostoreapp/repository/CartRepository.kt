package com.neosoft.neostoreapp.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.response.CartListResponse
import com.neosoft.neostoreapp.model.response.CartResponse
import com.neosoft.neostoreapp.network.ApiClient
import com.neosoft.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository(application: Application) {
    private val retrofitService = ApiClient.getRetrofitInstance().create(ApiService::class.java)

    fun getCartListResponse(
        request: CartRequest,
        mutableLiveData: MutableLiveData<CartListResponse>
    ): MutableLiveData<CartListResponse> {
        val response = request.accessToken?.let { retrofitService.getCartList(it) }

        response?.enqueue(object : Callback<CartListResponse> {

            override fun onResponse(call: Call<CartListResponse>, response: Response<CartListResponse>) {
                if (response.isSuccessful) {
                    Log.d("CartListRS", response.body().toString())
                    mutableLiveData.value = response.body()
                } else {
                    Log.d("CartListRU", response.errorBody().toString())
                    mutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<CartListResponse>, t: Throwable) {
                Log.d("CartListRF", t.message)
                mutableLiveData.value = null
            }

        })

        return mutableLiveData
    }

    fun getAddToCartResponse(
        request: CartRequest,
        mutableLiveData: MutableLiveData<CartResponse>
    ): MutableLiveData<CartResponse> {
        val response = retrofitService.addToCart(request.accessToken!!, request.productId!!, request.quantity!!)

        response.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body())
                    Log.d("AddCart RS", response.body().toString())
                } else {
                    mutableLiveData.postValue(null)
                    Log.d("AddCart RU", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                mutableLiveData.postValue(null)
                Log.d("AddCart RF", t.message)
            }

        })

        return mutableLiveData
    }

    fun getDeleteCartResponse(
        request: CartRequest,
        mutableLiveData: MutableLiveData<CartResponse>
    ): MutableLiveData<CartResponse> {
        val response = retrofitService.deleteCart(request.accessToken!!, request.productId!!)

        response.enqueue(object : Callback<CartResponse> {

            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body())
                    Log.d("DeleteCart RS", response.body().toString())
                } else {
                    mutableLiveData.postValue(null)
                    Log.d("DeleteCart RU", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                mutableLiveData.postValue(null)
                Log.d("DeleteCart RF", t.message)
            }

        })
        return mutableLiveData
    }

    fun getEditCartResponse(
        request: CartRequest,
        mutableLiveData: MutableLiveData<CartResponse>
    ): MutableLiveData<CartResponse> {
        val response = retrofitService.editCart(request.accessToken!!, request.productId!!, request.quantity!!)

        response.enqueue(object : Callback<CartResponse> {

            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                } else {
                    mutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                mutableLiveData.value = null
            }

        })

        return mutableLiveData
    }
}