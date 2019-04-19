package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.neosoft.neostoreapp.DemoCartItem
import com.neosoft.neostoreapp.R
import kotlinx.android.synthetic.main.item_cart_fragment.view.*

class CartItemAdapter(var cartItemsList: ArrayList<DemoCartItem>, var context: Context) :
    RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CartItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart_fragment, p0, false)
        return CartItemHolder(view)
    }

    override fun getItemCount(): Int {
        return cartItemsList.size
    }

    override fun onBindViewHolder(p0: CartItemHolder, p1: Int) {
        p0.cartItemTitle.text = cartItemsList[p1].title
        p0.cartItemCategory.text = cartItemsList[p1].category
        p0.cartItemPrice.text = cartItemsList[p1].price.toString()

        val quantities = arrayOf(1, 2, 3, 4, 5)
        val spinnerAdapter = ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,quantities)
        p0.cartItemQuantity.adapter = spinnerAdapter
//        p0.cartItemQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//        }
    }

    inner class CartItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewBackground: RelativeLayout = itemView.findViewById(R.id.rl_background_view)
        var viewForeground: RelativeLayout = itemView.findViewById(R.id.rl_foreground_view)
        var cartItemTitle: TextView = itemView.findViewById(R.id.txt_title_cart_item)
        var cartItemCategory: TextView = itemView.findViewById(R.id.txt_category_cart_item)
        var cartItemPrice: TextView = itemView.findViewById(R.id.txt_price_cart_item)
        var cartItemQuantity: Spinner = itemView.findViewById(R.id.spinner_item_count)
    }
}