package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.response.ProductResponse
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.view.adapter.DashboardAdapter
import com.neosoft.neostoreapp.view.adapter.DashViewPagerAdapter
import com.neosoft.neostoreapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment(), DashboardAdapter.OnDashboardClickListener {

    private var sliderImages = intArrayOf(R.drawable.slider_img1, R.drawable.slider_img2, R.drawable.slider_img3, R.drawable.slider_img4)
    private var productImages = arrayListOf(R.drawable.tableicon, R.drawable.sofaicon, R.drawable.chairsicon, R.drawable.cupboardicon)
    private lateinit var productViewModel: ProductViewModel
    lateinit var swipeTimer: Timer
    var currentImage = 0
    val imagesCount = sliderImages.size
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("State", "onCreateView")
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        sharedPreferences = context?.getSharedPreferences("MyPreferences",0)!!


        if (sharedPreferences.contains(Constants.CURRENT_IMG)){
            val strImageCount = sharedPreferences.getString(Constants.CURRENT_IMG,"0")
            currentImage = strImageCount?.toInt()!!
            autoSwipeImages()
        } else {
            autoSwipeImages()
        }

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("State", "onActivityCreated")
        Log.d("Status", "Initializing productViewModel")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("State", "onViewCreated")

        val viewPagerAdapter = DashViewPagerAdapter(context!!, sliderImages)
        viewPager.adapter = viewPagerAdapter

        val dashboardAdapter = DashboardAdapter(context!!, productImages)
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

        productViewModel.getProductListResponse().observe(this, Observer<ProductResponse> { t ->
        })
    }

    override fun onDashBoardClicked(pos: Int) {
        productViewModel.getProductList("${pos + 1}")
        Toast.makeText(context, "Tables", Toast.LENGTH_SHORT).show()
        val nextFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putInt("Position", pos + 1)
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
        sharedPreferences.edit().putString(Constants.CURRENT_IMG,"$currentImage").apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("State", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("State", "onDetach")
    }

    private fun autoSwipeImages(){
        Log.d("TAG","AUTO SWIPE")
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