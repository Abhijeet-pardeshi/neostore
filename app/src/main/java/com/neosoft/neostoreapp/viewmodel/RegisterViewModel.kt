package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.neosoft.neostoreapp.model.response.RegisterResponse
import com.neosoft.neostoreapp.model.request.RegisterRequest
import com.neosoft.neostoreapp.repository.RegisterRepository

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    //    var mutableLiveData : MutableLiveData<RegisterResponse>?= null
    var mutableLiveData: MutableLiveData<RegisterResponse>? = null
    private val registerRepository = RegisterRepository(application)

//    fun getRegResponse(): LiveData<RegisterResponse>{
//        if (mutableLiveData == null){
//            mutableLiveData = MutableLiveData()
//        }
//
//        return mutableLiveData as LiveData<RegisterResponse>
//    }

    fun getRegResponse(): LiveData<RegisterResponse> {

        if (mutableLiveData == null) {
            mutableLiveData = MutableLiveData()
        }

        return mutableLiveData as LiveData<RegisterResponse>
    }

    fun registerUser(request: RegisterRequest) {
        mutableLiveData = registerRepository.getRegResponse(request)
    }

}