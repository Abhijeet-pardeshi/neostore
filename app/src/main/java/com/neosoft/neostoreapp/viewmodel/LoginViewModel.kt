package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.LoginRequest
import com.neosoft.neostoreapp.model.response.LoginResponse
import com.neosoft.neostoreapp.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var mutableLiveData: MutableLiveData<LoginResponse>? = null// = MutableLiveData()
    private val loginRepository = LoginRepository(application)

    fun getLoginResponse(): LiveData<LoginResponse> {
        if (mutableLiveData == null) {
            mutableLiveData = MutableLiveData()
            Log.e("Status", "Initializing mutable livedata")
        }

        return mutableLiveData as LiveData<LoginResponse>
    }

    fun loginUser(request: LoginRequest) {
        //mutableLiveData!!.value = loginRepository.getLoginResponse(request).value
//        mutableLiveData.postValue(loginRepository.getLoginResponse(request).value)
        mutableLiveData?.value = loginRepository.getLoginResponse(request).value
    }
}