package com.neosoft.neostoreapp.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.response.ProductResponse
import com.neosoft.neostoreapp.model.response.ProductResponseData
import com.neosoft.neostoreapp.view.adapter.ProductAdapter
import com.neosoft.neostoreapp.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment(),ProductAdapter.OnProductClickListener {

    private var productResponseData: ProductResponseData? = null
    private var productsList: ArrayList<ProductResponseData> = arrayListOf()
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productResponseData?.let { productsList.add(it) }
        val bundle = arguments
        val pos = bundle?.getInt("Position", 0)
        productViewModel.getProductList("$pos")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter = ProductAdapter(productsList, context!!)
        rv_products.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        productAdapter.setProductClickListener(this)

        productViewModel.getProductListResponse().observe(this, Observer<ProductResponse> { t ->
            Log.d("Status", "getting Products")
//            Log.d("Products", t.toString())

            productsList.clear()
            t?.data.let {
                it?.forEach { product ->
                    productsList.add(product)
                    productAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onProductClicked(pos: Int) {
        val detailFragment = DetailFragment()

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container,detailFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}

//        rv_tables.setOnScrollListener(object: RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val pos = layoutManager.findLastVisibleItemPosition()
//
//                Toast.makeText(context,"0$pos OF ${productsList.size}",Toast.LENGTH_SHORT).show()
//            }
//        })

//        enableNavigationButtonBehavior()

//private fun enableNavigationButtonBehavior() {
//    //(activity as DrawerLocker).setDrawerEnabled(enabled = false)
//    (activity as DashBoardActivity).setDrawerEnabled(false)
//}
//
//private fun showProductsCount() {
//    val linearLayout = rv_tables.layoutManager as LinearLayoutManager
//    linearLayout.let { Toast.makeText(context,linearLayout.findFirstVisibleItemPosition().toString(),Toast.LENGTH_SHORT).show() }
//}
//
//override fun onAttach(activity: Activity?) {
//    super.onAttach(activity)
//
//}