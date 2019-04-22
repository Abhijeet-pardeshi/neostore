package com.neosoft.neostoreapp.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.CartListItem
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.response.CartListResponse
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.utils.RecyclerItemTouchHelper
import com.neosoft.neostoreapp.view.adapter.CartItemAdapter
import com.neosoft.neostoreapp.viewmodel.DeleteCartViewModel
import com.neosoft.neostoreapp.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.ArrayList

class CartFragment : Fragment(), RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private var cartItems: ArrayList<CartListItem>? = null
    var accessToken: String? = null
    lateinit var listCartViewModel: ListCartViewModel
    lateinit var sharedPreferences: SharedPreferences
    lateinit var cartItemAdapter: CartItemAdapter
    var productId: Int? = null
    lateinit var deleteCartViewModel: DeleteCartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartItems = arrayListOf()
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        listCartViewModel = ViewModelProviders.of(this).get(ListCartViewModel::class.java)
        deleteCartViewModel = ViewModelProviders.of(this).get(DeleteCartViewModel::class.java)
        accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
        val quantity = arguments?.getInt(Constants.QUANTITY_COUNT)
        val productId = arguments?.getInt(Constants.PRODUCT_ID)

        getCartList()

        //listening for the data changes
        listCartViewModel.getCartListResponse().observe(this, Observer { cartListResponse ->
            Log.d("Cart VM:-", cartListResponse.toString())
            cartListResponse?.let { populateCartList(it) }
        })

        deleteCartViewModel.getDeleteCartResponse().observe(this, Observer { deleteResponse ->
            Log.d("Delete VM:-", deleteResponse.toString())
            Toast.makeText(context, deleteResponse?.userMessage, Toast.LENGTH_SHORT).show()
        })

        cartItemAdapter = CartItemAdapter(cartItems!!, this.context!!)
        rv_cart_fragment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartItemAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_cart_fragment)
    }

    private fun populateCartList(cartListResponse: CartListResponse) {
        val response: CartListResponse? = cartListResponse
        val responseData = response?.cartListResponseData
        responseData.let {
            it?.forEach { cartItem ->
                val quantityCount = cartItem.quantity
                val responseProduct = cartItem.product
                val id = responseProduct?.id
                val name = responseProduct?.name
                val category = responseProduct?.productCategory
                val productImages = responseProduct?.productImages
                val subTotal = responseProduct?.subTotal
                Log.d("Cart VM", "$quantityCount $responseProduct $name $category $productImages $subTotal $id")
                val singleCartItem = CartListItem(id, productImages, name, category, quantityCount, subTotal)
                cartItems?.add(singleCartItem)
                cartItemAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getCartList() {
        listCartViewModel.getCartList(CartRequest(accessToken!!))
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is CartItemAdapter.CartItemHolder) {
            val name = cartItems?.get(viewHolder.adapterPosition)?.cartItemName
            val id = cartItems?.get(viewHolder.adapterPosition)?.cartItemId
            Toast.makeText(context, "$name $id", Toast.LENGTH_SHORT).show()

            deleteCartViewModel.deleteCart(CartRequest(accessToken!!, id!!))
            cartItems?.remove(cartItems?.get(position))
            cartItemAdapter.notifyDataSetChanged()
        }
    }
}