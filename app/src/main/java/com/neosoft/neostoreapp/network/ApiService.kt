package com.neosoft.neostoreapp.network

import com.neosoft.neostoreapp.model.response.DetailResponse
import com.neosoft.neostoreapp.model.response.LoginResponse
import com.neosoft.neostoreapp.model.response.ProductResponse
import com.neosoft.neostoreapp.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("users/login")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("users/register")
    @FormUrlEncoded
    fun registerUser(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("gender") gender: String,
        @Field("phone_no") phoneNo: String
    ): Call<RegisterResponse>

    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String, @Query("limit") limit: Int, @Query("page") page: Int): Call<ProductResponse>

    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String): Call<ProductResponse>

    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String, @Query("limit") limit: Int): Call<ProductResponse>

    @GET("products/getDetail")
    fun getDetail(@Query("product_id") productId: Int): Call<DetailResponse>
}