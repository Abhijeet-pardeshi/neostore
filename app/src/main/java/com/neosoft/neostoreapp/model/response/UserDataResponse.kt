package com.neosoft.neostoreapp.model.response

import com.google.gson.annotations.SerializedName

data class UserDataResponse(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("role_id")
    var roleId: String? = null,

    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var userName: String? = null,

    @SerializedName("profile_pic")
    var profilePic: String? = null,

    @SerializedName("country_id")
    var countryId: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("phoneNo")
    var phoneNo: String? = null,

    @SerializedName("dob")
    var dob: String? = null,

    @SerializedName("is_active")
    var isActive: String? = null,

    @SerializedName("created")
    var created: String? = null,

    @SerializedName("modified")
    var modified: String? = null,

    @SerializedName("access_token")
    var accessToken: String? = null
    )