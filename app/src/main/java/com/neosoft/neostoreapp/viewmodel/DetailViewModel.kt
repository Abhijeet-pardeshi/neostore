package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.DetailRequest
import com.neosoft.neostoreapp.model.response.DetailResponse
import com.neosoft.neostoreapp.repository.DetailRepository

class DetailViewModel(application: Application) : AndroidViewModel(application){
    var mutableLiveData: MutableLiveData<DetailResponse>? = null
    private val detailRepository = DetailRepository(application)

    fun getDetailResponse(): LiveData<DetailResponse>{
        return mutableLiveData as LiveData<DetailResponse>
    }

    init {
        mutableLiveData = MutableLiveData()
    }

    fun getDetails(request: DetailRequest){
        Log.d("Detail",detailRepository.getDetailResponse(request).value.toString())
        mutableLiveData = detailRepository.getDetailResponse(request)
    }
}