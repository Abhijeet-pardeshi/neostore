package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.request.DetailRequest
import com.neosoft.neostoreapp.model.response.DetailImagesResponse
import com.neosoft.neostoreapp.model.response.DetailResponseData
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.view.adapter.DetailImagesAdapter
import com.neosoft.neostoreapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_image_detail.*

class DetailFragment : Fragment(), DetailImagesAdapter.OnImageClickListener {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var imagesUrlsList: ArrayList<DetailImagesResponse>
    private lateinit var details: DetailResponseData
    private var productId: Int? = null
    lateinit var detailImagesAdapter: DetailImagesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        val bundle = arguments
        productId = bundle?.getInt(Constants.PRODUCT_ID)
        detailViewModel.getDetails(DetailRequest(this.productId!!))
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetails()

        btn_buy.setOnClickListener {
            val cartFragment = CartFragment()
            fragmentManager?.
                beginTransaction()?.
                replace(R.id.container, cartFragment)?.
                addToBackStack(null)?.
                commit()
        }
//
//            when (imagesUrlsList.size) {
//                1 -> loadOneImage()
//                2 -> loadTwoImages()
//                3 -> loadThreeImages()
//                4 -> loadFourImages()
//            }

//            loadImages()
//
//            //changing large image as per the small image click
//            val images = arrayListOf(iv_small_pic1_detail, iv_small_pic2_detail, iv_small_pic3_detail)
//            for (index in images.indices) {
//                images[index].setOnClickListener {
//                    Picasso.get().load(imagesUrlsList[index].image).into(iv_large_pic_detail)
//                    images[index].setBackgroundResource(R.drawable.img_red_border_bg)
//
//                    images.forEach { image ->
//                        if (image != images[index]) {
//                            image.setBackgroundResource(0)
//                        }
//                    }
//                }
//            }
    }

    private fun getDetails() {
        detailViewModel.getDetailResponse().observe(this, Observer { product ->
            details = product?.data!!
            loadDetails()
            loadImages()
        })
    }

    private fun loadDetails() {
        txt_title_detail.text = details.name
        txt_producer_detail.text = details.producer
        txt_price_detail.text = "Rs. ${details.cost}"
        rb_rating_detail.rating = details.rating?.toFloat()!!
        txt_desc_info_detail.text = details.description
    }

    private fun loadImages() {
        imagesUrlsList = details.productImages!!
        Log.d("Urls", "${imagesUrlsList.size}")

        detailImagesAdapter = DetailImagesAdapter(imagesUrlsList, context!!)
        rv_detail_images.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = detailImagesAdapter
        }

        detailImagesAdapter.setImageClickListener(this)
        Picasso.get().load(imagesUrlsList[0].image).into(iv_large_pic_detail)
    }

    override fun onImageCliked(pos: Int) {
        Picasso.get().load(imagesUrlsList[pos].image).into(iv_large_pic_detail)
    }
}

//    private fun loadOneImage() {
//        Picasso.get().load(imagesUrlsList[0].image).into(iv_large_pic_detail)
//        Picasso.get().load(imagesUrlsList[0].image).into(iv_small_pic1_detail)
//    }
//
//    private fun loadTwoImages(){
//        loadOneImage()
//        iv_small_pic2_detail.visibility = View.VISIBLE
//        Picasso.get().load(imagesUrlsList[1].image).into(iv_small_pic2_detail)
//    }
//
//    private fun loadThreeImages(){
//        loadTwoImages()
//        iv_small_pic3_detail.visibility = View.VISIBLE
//        Picasso.get().load(imagesUrlsList[2].image).into(iv_small_pic3_detail)
//    }
//
//    private fun loadFourImages(){
//        loadThreeImages()
//        iv_small_pic4_detail.visibility = View.VISIBLE
//        Picasso.get().load(imagesUrlsList[3].image).into(iv_small_pic4_detail)

//    private fun loadFourImages() {
//        Picasso.get().load(imagesUrlsList[0].image).into(iv_large_pic_detail)
//        Picasso.get().load(imagesUrlsList[0].image).into(iv_small_pic1_detail)
//        Picasso.get().load(imagesUrlsList[1].image).into(iv_small_pic2_detail)
//        Picasso.get().load(imagesUrlsList[2].image).into(iv_small_pic3_detail)
//    }