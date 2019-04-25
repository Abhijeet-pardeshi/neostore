package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.neosoft.neostoreapp.R
import com.squareup.picasso.Picasso

class DashboardItemAdapter(private var context: Context, var productsList: ArrayList<String>) :
    RecyclerView.Adapter<DashboardItemAdapter.ProductHolder>() {

    interface OnDashboardClickListener {
        fun onDashBoardClicked(pos: Int)
    }

    lateinit var onDashboardClickListener: OnDashboardClickListener

    fun setProductClickListener(onDashBoardClickListener: OnDashboardClickListener) {
        this.onDashboardClickListener = onDashBoardClickListener
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): ProductHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_product_dashboard, container, false)
        return ProductHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, pos: Int) {
        Picasso.get().load(productsList.get(pos)).into(holder.productImage)
    }

    inner class ProductHolder(itemView: View) : ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.iv_product_home)

        init {
            productImage.setOnClickListener {
                onDashboardClickListener.let { onDashboardClickListener.onDashBoardClicked(adapterPosition) }
            }
        }
    }
}