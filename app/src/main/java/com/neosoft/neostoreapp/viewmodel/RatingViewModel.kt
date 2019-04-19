package com.neosoft.neostoreapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.neosoft.neostoreapp.model.request.SetRatingRequest
import com.neosoft.neostoreapp.model.response.RatingResponse
import com.neosoft.neostoreapp.repository.RatingRepository

class RatingViewModel(application: Application) : AndroidViewModel(application) {

    private var mutableLiveData: MutableLiveData<RatingResponse>? = null
    private val ratingRepository = RatingRepository(application)

    fun getRatingResponse(): LiveData<RatingResponse> {

        Log.d("Rating Res",mutableLiveData.toString())
        return mutableLiveData as LiveData<RatingResponse>
    }

    init {
        mutableLiveData = MutableLiveData()
    }

    fun setRating(request: SetRatingRequest){
        ratingRepository.getRatingResponse(request, mutableLiveData!!)
    }
}