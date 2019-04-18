package com.neosoft.neostoreapp.network

import com.neosoft.neostoreapp.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("users/login")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    /**
     * registration
     */
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

    /**
     * products list
     */
    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String, @Query("limit") limit: Int, @Query("page") page: Int): Call<ProductResponse>

    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String): Call<ProductResponse>

    @GET("products/getList")
    fun getProducts(@Query("product_category_id") productCategoryId: String, @Query("limit") limit: Int): Call<ProductResponse>

    /**
     * products details
     */
    @GET("products/getDetail")
    fun getDetail(@Query("product_id") productId: Int): Call<DetailResponse>

    /**
     * add product to the cart
     */
    @POST("addToCart")
    @FormUrlEncoded
    fun addToCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: String,
        @Field("quantity") quantity: String
    ): Call<CartResponse>

    /**
     * edit the cart
     */
    @POST("editCart")
    @FormUrlEncoded
    fun editCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: String,
        @Field("quantity") quantity: String
    ): Call<CartResponse>

    /**
     * delete the cart
     */
    @POST("deleteCart")
    @FormUrlEncoded
    fun deleteCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: String
    )

    /**
     * show the cart
     */
    @GET("cart")
    fun getCartList(
        @Header("access_token") accessToken: String
    )

    /**
     * order the products
     */
    @POST("order")
    @FormUrlEncoded
    fun orderProduct(
        @Header("access_token") accessToken: String,
        @Field("address") address: String
    )

    /**
     * fetch the order list
     */
    @GET("orderList")
    fun getOrderList(
        @Header("access_token") accessToken: String
    )

    /**
     * fetch the order detail
     */
    @GET("orderDetail")
    fun getOrderDetail(
        @Header("access_token") accessToken: String,
        @Query("order_id") orderId: Int
    )

    /**
     * set the rating for product
     */
    @POST("products/setRating")
    @FormUrlEncoded
    fun setRating(
        @Field("product_id") productId: String,
        @Field("rating") rating: String
    )

    /**
     * forgot the password
     */
    @POST("users/forgot")
    @FormUrlEncoded
    fun forgotPassword(
        @Field("email") email: String
    )

    /**
     * change the password
     */
    @POST("users/change")
    @FormUrlEncoded
    fun changePassword(
        @Header("access_token") accessToken: String,
        @Field("old_password") oldPassword: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String
    )

    /**
     * update account details
     */
    @POST("users/update")
    @FormUrlEncoded
    fun updateUser(
        @Header("access_token") accessToken: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
        @Field("phone_no") phoneNo: String,
        @Field("profile_pic") profilePic: String
    )

    /**
     * fetch account details
     */
    @GET("users/getUserData")
    fun getUsersData(
        @Header("access_token") accessToken: String
    )
}