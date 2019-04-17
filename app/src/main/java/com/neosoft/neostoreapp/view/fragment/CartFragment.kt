package com.neosoft.neostoreapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.neostoreapp.DemoCartItem
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.view.adapter.CartItemAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.ArrayList

class CartFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartItems = ArrayList<DemoCartItem>()
        cartItems.add(DemoCartItem("Title","Category",11))
        val cartItemAdapter = CartItemAdapter(cartItems, this.context!!)

        rv_cart_fragment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartItemAdapter
        }
    }
}