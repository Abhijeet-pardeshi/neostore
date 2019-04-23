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
import com.neosoft.neostoreapp.viewmodel.EditCartViewModel
import com.neosoft.neostoreapp.viewmodel.ListCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.*

class CartFragment : Fragment(), RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,
    CartItemAdapter.OnQuantityChangeListener {

    private var cartItems: ArrayList<CartListItem>? = null
    var accessToken: String? = null
    var productId: Int? = null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var cartItemAdapter: CartItemAdapter
    lateinit var listCartViewModel: ListCartViewModel
    lateinit var deleteCartViewModel: DeleteCartViewModel
    lateinit var editCartViewModel: EditCartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartItems = arrayListOf()
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        listCartViewModel = ViewModelProviders.of(this).get(ListCartViewModel::class.java)
        deleteCartViewModel = ViewModelProviders.of(this).get(DeleteCartViewModel::class.java)
        editCartViewModel = ViewModelProviders.of(this).get(EditCartViewModel::class.java)
        accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")

        getCartList()

        cartItemAdapter = CartItemAdapter(cartItems!!, this.context!!)
        rv_cart_fragment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartItemAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        cartItemAdapter.setQuantityChangeListener(this)
        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_cart_fragment)

        deleteCartViewModel.getDeleteCartResponse().observe(this, Observer { deleteResponse ->
            Log.d("Delete VM:-", deleteResponse.toString())
            Toast.makeText(context, deleteResponse?.userMessage, Toast.LENGTH_SHORT).show()

            getCartList()
        })

        editCartViewModel.getEditCartResponse().observe(this, Observer { editResponse ->
            Log.d("Edit VM:-", editResponse.toString())
            Toast.makeText(context, editResponse?.userMessage, Toast.LENGTH_SHORT).show()

//            getCartList()
        })
    }

    private fun populateCartList(cartListResponse: CartListResponse) {
        cartItems?.clear()
        val response: CartListResponse? = cartListResponse
        txt_total_price_cart.text = response?.total
        val responseData = response?.cartListResponseData

        if (response != null) {
            if (response.cartListResponseData == null) {
                rl_total_container.visibility = View.GONE
                rv_cart_fragment.visibility = View.GONE
                btn_order_product.visibility = View.GONE
                img_empty_cart_fragment.visibility = View.VISIBLE
            }
        }

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
                Log.d("Total CF", response?.total ?: "0")
                cartItems?.add(singleCartItem)
            }
        }
        cartItemAdapter.notifyDataSetChanged()
    }

    private fun getCartList() {
        listCartViewModel.getCartList(CartRequest(accessToken!!))

        listCartViewModel.getCartListResponse().observe(this, Observer { cartListResponse ->
            Log.d("Cart VM:-", cartListResponse.toString())
            cartListResponse?.let { populateCartList(it) }
        })
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is CartItemAdapter.CartItemHolder) {
            val name = cartItems?.get(viewHolder.adapterPosition)?.cartItemName
            val id = cartItems?.get(viewHolder.adapterPosition)?.cartItemId
            Toast.makeText(context, "$name $id", Toast.LENGTH_SHORT).show()
            deleteCartViewModel.deleteCart(CartRequest(accessToken!!, id!!))
        }
    }

//    override fun onQuantityChanged(quantity: String, productId: String, pos: Int) {
    override fun onQuantityChanged(quantity: String, productId: String, pos: Int) {

    Toast.makeText(context, quantity, Toast.LENGTH_SHORT).show()
        editCartViewModel.editCart(CartRequest(accessToken!!, productId, quantity))
        updateTotal(pos)
    }

    private fun updateTotal(pos: Int) {
        getCartList()
    }
}