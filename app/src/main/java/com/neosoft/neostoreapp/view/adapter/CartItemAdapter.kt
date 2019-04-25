package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.CartListItem
import com.squareup.picasso.Picasso


class CartItemAdapter(var cartItemsList: ArrayList<CartListItem>, var context: Context) :
    RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {
    var quantity: Int? = null
    var quantities: ArrayList<Int>? = null

    interface OnQuantityChangeListener {
        fun onQuantityChanged(quantity: String, productId: String, pos: Int)
    }

    var onQuantityChangeListener: OnQuantityChangeListener? = null

    fun setQuantityChangeListener(onQuantityChangeListener: OnQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CartItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart_fragment, p0, false)
        return CartItemHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("CartItems", "${cartItemsList.size}")
        return cartItemsList.size
    }

    override fun onBindViewHolder(p0: CartItemHolder, p1: Int) {
        quantities = arrayListOf<Int>()
        for (num in 1..8) {
            quantities!!.add(num)
        }
        val spinnerAdapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, quantities)
        p0.cartItemQuantity.adapter = spinnerAdapter
        bindData(p0, p1)
    }

    private fun bindData(cartHolder: CartItemHolder, pos: Int) {
        quantity = cartItemsList[pos].cartItemQuantity?.toInt()
        Picasso.get().load(cartItemsList[pos].cartItemImage).into(cartHolder.cartItemImage)
        quantity?.let { cartHolder.cartItemQuantity.setSelection(it - 1,false) }
        cartHolder.cartItemTitle.text = cartItemsList[pos].cartItemName
        cartHolder.cartItemCategory.text = cartItemsList[pos].cartItemCategory
        cartHolder.cartItemPrice.text = cartItemsList[pos].cartItemTotal


        /*val spnValue = cartHolder.cartItemQuantity.getSelectedItem().toString()

        onQuantityChangeListener?.onQuantityChanged(
            spnValue,
            cartItemsList[pos].cartItemId!!,
            pos
        )*/

        var userSelect = false
        cartHolder.cartItemQuantity.setOnTouchListener { v, event ->
            userSelect = true
            false
        }

        cartHolder.cartItemQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                quantity = quantities[position-1]
                if (userSelect) {
                    Toast.makeText(context, "onItemSelect", Toast.LENGTH_SHORT).show()
                    quantity = quantities?.get(position)
                    onQuantityChangeListener.let { onQuantityChangeListener ->
                        if (cartItemsList.size != 0) {
                            onQuantityChangeListener?.onQuantityChanged(
                                quantity.toString(),
                                cartItemsList[pos].cartItemId!!,
                                pos
                            )
                        }
                    }
                    userSelect = false
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
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