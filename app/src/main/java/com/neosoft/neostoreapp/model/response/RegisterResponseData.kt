package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseData(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("role_id")
    var roleId: Int? = null,

    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var userName: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("phone_no")
    var phoneNo: String? = null,

    @SerializedName("is_active")
    var isActive: Boolean? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("access_token")
    var accessToken: String? = null

)