package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.request.AccountRequest
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.view.adapter.DashboardItemAdapter
import com.neosoft.neostoreapp.view.adapter.DashViewPagerAdapter
import com.neosoft.neostoreapp.viewmodel.AccDetailsViewModel
import com.neosoft.neostoreapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment(), DashboardItemAdapter.OnDashboardClickListener {

    private var productImages =
        intArrayOf(R.drawable.tableicon, R.drawable.chairsicon, R.drawable.sofaicon, R.drawable.cupboardicon)
    private var sliderImages =
        intArrayOf(R.drawable.slider_img1, R.drawable.slider_img2, R.drawable.slider_img3, R.drawable.slider_img4)
    private var categoryImages = ArrayList<String>()
    private lateinit var productViewModel: ProductViewModel
    private lateinit var accDetailViewModel: AccDetailsViewModel
    lateinit var swipeTimer: Timer
    var currentImage = 0
    var accessToken: String? = null
    val imagesCount = sliderImages.size
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("State", "onCreateView")
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        accDetailViewModel = ViewModelProviders.of(this).get(AccDetailsViewModel::class.java)
        if (sharedPreferences.contains(Constants.ACCESS_TOKEN)) {
            accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
        }

        loadCategories()

        if (sharedPreferences.contains(Constants.CURRENT_IMG)) {
            val strImageCount = sharedPreferences.getString(Constants.CURRENT_IMG, "0")
            currentImage = strImageCount?.toInt()!!
            autoSwipeImages()
        } else {
            autoSwipeImages()
        }

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    private fun loadCategories() {
        accDetailViewModel.getAccountDetails(AccountRequest(accessToken!!))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("State", "onActivityCreated")
        Log.d("Status", "Initializing productViewModel")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("State", "onViewCreated")
        accDetailViewModel.getAccountDetailsResponse().observe(this, Observer { response ->
            val accountDetailsData = response?.data
            val userEmail = accountDetailsData?.userDataResponse?.email
            val userName = accountDetailsData?.userDataResponse?.userName
            if (!sharedPreferences.contains(Constants.USER_EMAIL)) {
                sharedPreferences.edit().putString(Constants.USER_EMAIL, userEmail).apply()
            }
            if (!sharedPreferences.contains(Constants.USER_NAME)) {
                sharedPreferences.edit().putString(Constants.USER_NAME, userName).apply()
            }
            val productCategories = accountDetailsData?.productCategoriesResponse
            productCategories?.forEach { category ->
                val iconImage = category.iconImage
                iconImage?.let { categoryImages.add(it) }
            }
        })

//        accDetailViewModel.getAccountDetailsResponse().observe(this, Observer { accountDetailsResponse->
//            var accountDetailsData = accountDetailsResponse?.data
//            var productCategories = accountDetailsData?.productCategoriesResponse
//            pro
//        })

        val viewPagerAdapter = DashViewPagerAdapter(context!!, sliderImages)
        viewPager.adapter = viewPagerAdapter

//        val dashboardAdapter = DashboardItemAdapter(context!!, categoryImages)
        val dashboardAdapter = DashboardItemAdapter(context!!, categoryImages)
        rv_products_home.layoutManager = GridLayoutManager(context!!, 2)
        rv_products_home.adapter = dashboardAdapter
        dashboardAdapter.setProductClickListener(this)

        //detecting current image
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                currentImage = position
            }
        })

        //auto swipe functionality

        tl_dots.setupWithViewPager(viewPager, true)

//        productViewModel.getProductListResponse().observe(this, Observer<ProductResponse> { t ->
//        })
    }

    override fun onDashBoardClicked(pos: Int) {
        productViewModel.getProductList("${pos + 1}")
        Toast.makeText(context, "Tables", Toast.LENGTH_SHORT).show()
        val nextFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putInt("Position", pos + 1)
//        bundle.putString(Constants.ACCESS_TOKEN,accessToken)
        nextFragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.add(R.id.container, nextFragment, nextFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d("State", "onStart")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("State", "onAttach")
    }

    override fun onResume() {
        super.onResume()
        Log.d("State", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("State", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("State", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("State", "onDestroyView")
        swipeTimer.cancel()
        sharedPreferences.edit().putString(Constants.CURRENT_IMG, "$currentImage").apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("State", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("State", "onDetach")
    }

    fun autoSwipeImages() {
        Log.d("TAG", "AUTO SWIPE")
        swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                if (isVisible) {
                    activity?.runOnUiThread {
                        currentImage.let {
                            if (currentImage == imagesCount) {
                                Log.d("TAG_IMG", "$currentImage")
                                currentImage = 0
                            }
                            viewPager.let {
                                Log.d("SET_IMG", "$currentImage")
                                Log.d("MAX_IMG", "$imagesCount")
                                viewPager.setCurrentItem(currentImage++, true)
                            }
                        }
                    }
                }
            }

        }, 800, 3000)
    }
}

//    private fun getNextFragment(pos: Int): Fragment {
//        var fragment: Fragment? = null
//        when (pos + 1) {
//            1 -> fragment = ProductFragment()
//            2 -> fragment = ProductFragment()
//            3 -> fragment = ProductFragment()
//            4 -> fragment = ProductFragment()
//        }
//
//        return fragment!!
//    }

//    private fun disableNavigationBehavior() {
//        (activity as DashBoardActivity).setDrawerEnabled(true)
//    }

//        disableNavigationBehavior()