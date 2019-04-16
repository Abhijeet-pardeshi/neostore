package com.neosoft.neostoreapp.network

import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.neosoft.neostoreapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        var retrofitClientInstance: Retrofit? = null

        fun getRetrofitInstance(): Retrofit {
            if (retrofitClientInstance == null) {
                retrofitClientInstance = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()
            }

            return retrofitClientInstance as Retrofit
        }

        fun getHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())


            return httpClient.build()
        }
    }

}