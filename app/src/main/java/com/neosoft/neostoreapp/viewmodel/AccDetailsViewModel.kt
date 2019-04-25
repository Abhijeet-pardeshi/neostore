package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.neosoft.neostoreapp.model.request.AccountRequest
import com.neosoft.neostoreapp.model.response.AccDetailsResponse
import com.neosoft.neostoreapp.repository.AccountRepository

class AccDetailsViewModel(application: Application) : AndroidViewModel(application) {
    var mutableLiveData: MutableLiveData<AccDetailsResponse>? = null
    var accountRepository = AccountRepository(application)

    fun getAccountDetailsResponse(): LiveData<AccDetailsResponse> {
        return mutableLiveData as LiveData<AccDetailsResponse>
    }

    init {
        mutableLiveData = MutableLiveData()
    }

    fun getAccountDetails(request: AccountRequest) {
        mutableLiveData?.let { accountRepository.getAccountDetailsResponse(request, it) }
    }
}