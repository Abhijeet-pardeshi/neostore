package com.neosoft.neostoreapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.neosoft.neostoreapp.DemoCartItem
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.utils.RecyclerItemTouchHelper
import com.neosoft.neostoreapp.view.adapter.CartItemAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.ArrayList

class CartFragment : Fragment(), RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private val cartItems: ArrayList<DemoCartItem>? = null
    lateinit var bundle: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartItems = ArrayList<DemoCartItem>()
        bundle = Bundle()
        cartItems.add(DemoCartItem("Title", "Category", 11))
        val cartItemAdapter = CartItemAdapter(cartItems, this.context!!)

        rv_cart_fragment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartItemAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_cart_fragment)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is CartItemAdapter.CartItemHolder) {
            val name = cartItems?.get(viewHolder.adapterPosition)?.title
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
        }
    }
}