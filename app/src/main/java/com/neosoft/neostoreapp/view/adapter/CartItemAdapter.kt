package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.neosoft.neostoreapp.DemoCartItem
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.CartListItem
import com.neosoft.neostoreapp.model.request.CartRequest
import com.neosoft.neostoreapp.model.response.CartResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart_fragment.view.*

class CartItemAdapter(var cartItemsList: ArrayList<CartListItem>, var context: Context) :
    RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {
    var quantity: Int? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CartItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart_fragment, p0, false)
        return CartItemHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("CartItems","${cartItemsList.size}")
        return cartItemsList.size
    }

    override fun onBindViewHolder(p0: CartItemHolder, p1: Int) {
//        p0.cartItemTitle.text = cartItemsList[p1].title
//        p0.cartItemCategory.text = cartItemsList[p1].category
//        p0.cartItemPrice.text = cartItemsList[p1].price.toString()


        val quantities = arrayListOf<Int>()
        for (num in 1..100){
            quantities.add(num)
        }
        val spinnerAdapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, quantities)
        p0.cartItemQuantity.adapter = spinnerAdapter
        bindData(p0, p1)

        p0.cartItemQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                quantity = quantities[position-1]
                Log.d("Spinner",quantity.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun bindData(cartHolder: CartItemHolder, pos: Int) {
        quantity = cartItemsList[pos].cartItemQuantity?.toInt()
        Picasso.get().load(cartItemsList[pos].cartItemImage).into(cartHolder.cartItemImage)
        quantity?.let { cartHolder.cartItemQuantity.setSelection(it - 1) }
        cartHolder.cartItemTitle.text = cartItemsList[pos].cartItemName
        cartHolder.cartItemCategory.text = cartItemsList[pos].cartItemCategory
        cartHolder.cartItemPrice.text = cartItemsList[pos].cartItemTotal
    }

    inner class CartItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewBackground: RelativeLayout = itemView.findViewById(R.id.rl_background_view)
        var viewForeground: RelativeLayout = itemView.findViewById(R.id.rl_foreground_view)
        var cartItemImage: ImageView = itemView.findViewById(R.id.img_cart_item)
        var cartItemTitle: TextView = itemView.findViewById(R.id.txt_title_cart_item)
        var cartItemCategory: TextView = itemView.findViewById(R.id.txt_category_cart_item)
        var cartItemPrice: TextView = itemView.findViewById(R.id.txt_price_cart_item)
        var cartItemQuantity: Spinner = itemView.findViewById(R.id.spinner_item_count)
    }
}