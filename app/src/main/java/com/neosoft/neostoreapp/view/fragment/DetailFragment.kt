package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.request.DetailRequest
import com.neosoft.neostoreapp.model.request.SetRatingRequest
import com.neosoft.neostoreapp.model.response.DetailImagesResponse
import com.neosoft.neostoreapp.model.response.DetailResponseData
import com.neosoft.neostoreapp.model.response.RatingResponseData
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.view.adapter.DetailImagesAdapter
import com.neosoft.neostoreapp.viewmodel.AddCartViewModel
import com.neosoft.neostoreapp.viewmodel.DetailViewModel
import com.neosoft.neostoreapp.viewmodel.RatingViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), DetailImagesAdapter.OnImageClickListener,
    QuantityFragment.OnQuantitySubmitListener, RatingFragment.OnRatingSubmitListener {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var addCartViewModel: AddCartViewModel
    private lateinit var imagesUrlsList: ArrayList<DetailImagesResponse>
    private lateinit var details: DetailResponseData
    private lateinit var rating: RatingResponseData
    private var productId: Int? = null
    lateinit var detailImagesAdapter: DetailImagesAdapter
    lateinit var ratingFragment: RatingFragment
    lateinit var quantityFragment: QuantityFragment
    lateinit var sharedPreferences: SharedPreferences
    lateinit var accessToken: String

    private lateinit var ratingViewModel: RatingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        ratingViewModel = ViewModelProviders.of(this).get(RatingViewModel::class.java)
        addCartViewModel = ViewModelProviders.of(this).get(AddCartViewModel::class.java)
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        val bundle = arguments
        accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN,"")!!
        ratingFragment = RatingFragment()
        quantityFragment = QuantityFragment()
        productId = bundle?.getInt(Constants.PRODUCT_ID)
        detailViewModel.getDetails(DetailRequest(this.productId!!))
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetails()

        btn_buy.setOnClickListener {
            //
//            val dialogBuilder = AlertDialog.Builder(context!!)
//            val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_quantity,rl_detail_container,false)
//            dialogBuilder.setView(dialogView)
//            dialogBuilder.create()
//
//            dialogBuilder.show(
            showQuantityDialog()
        }

        btn_rate.setOnClickListener {
            showRatingDialog()
        }

        ratingViewModel.getRatingResponse().observe(this, Observer { ratingResponse ->
            //            ratingResponse?.data?.let { ratingData ->
//                rating = ratingData
//                Log.d("R Observer", rating.toString())
//            }
//
//            ratingResponse?.userMessage?.let {
//                Log.d("UserMessage", it)
//            }
            ratingResponse?.userMessage?.let {
                Toast.makeText(context, ratingResponse.userMessage, Toast.LENGTH_SHORT).show()
            }
        })

        addCartViewModel.getAddToCartResponse().observe(this, Observer { cartResponse ->
            cartResponse?.userMessage.let {
                Toast.makeText(context, cartResponse?.userMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDetails() {
        detailViewModel.getDetailResponse().observe(this, Observer { product ->
            details = product?.data!!
            Log.d("Detail VM", product.data.toString())
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


    private fun showQuantityDialog() {
        val quantityFragment = QuantityFragment()
        val bundle = Bundle()
        val fragmentManager = fragmentManager
        bundle.putString(Constants.PRODUCT_TITLE, details.name)
        bundle.putString(Constants.PRODUCT_IMG, imagesUrlsList[0].image)
        quantityFragment.arguments = bundle
        quantityFragment.setTargetFragment(this, 200)
        quantityFragment.show(fragmentManager, "Quantity Fragment")
    }

    override fun onQauntitySubmit(quantity: Int) {

        val cartFragment = CartFragment()
//        bundleArguments()
        val bundle = Bundle()
        bundle.putInt(Constants.QUANTITY_COUNT, quantity)
        productId?.let { bundle.putInt(Constants.PRODUCT_ID, it) }
        bundle.putString(Constants.ACCESS_TOKEN, accessToken)
        cartFragment.arguments = bundle
        addProductToCart(CartRequest(accessToken, productId.toString(), quantity.toString()))
        fragmentManager?.beginTransaction()?.replace(R.id.container, cartFragment)?.addToBackStack(null)?.commit()
    }

    private fun addProductToCart(cartRequest: CartRequest) {
        addCartViewModel.addToCart(cartRequest)
    }

    private fun showRatingDialog() {
        val ratingFragment = RatingFragment()
        val bundle = Bundle()
        val fragmentManager = fragmentManager
        bundle.putString(Constants.PRODUCT_TITLE, details.name)
        bundle.putString(Constants.PRODUCT_IMG, imagesUrlsList[0].image)
        ratingFragment.arguments = bundle
        ratingFragment.setTargetFragment(this, 100)
        ratingFragment.show(fragmentManager, "Rating Fragment")
    }

    override fun onRatingSubmit(rating: Float) {
        val request = SetRatingRequest(productId.toString(), rating.toString())
        ratingViewModel.setRating(request)
    }
}

