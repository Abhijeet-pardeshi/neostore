package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.request.DetailRequest
import com.neosoft.neostoreapp.repository.DetailRepository
import com.neosoft.neostoreapp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        detailViewModel.getDetails(DetailRequest(4))
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getDetailResponse().observe(this, Observer { product ->
            Log.d("Detail fm",product.toString())
        })
    }
}