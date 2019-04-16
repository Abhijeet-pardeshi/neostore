package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.response.ProductResponseData
import com.squareup.picasso.Picasso

class ProductAdapter(var tablesList: ArrayList<ProductResponseData>, var context: Context): RecyclerView.Adapter<ProductAdapter.TableViewHolder>() {

    interface OnProductClickListener{
        fun onProductClicked(pos: Int)
    }

    lateinit var onProductClickListener: OnProductClickListener

    fun setProductClickListener(onProductClickListener: OnProductClickListener){
        this.onProductClickListener = onProductClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): TableViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_fragment,parent,false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tablesList.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, pos: Int) {
        holder.tableName.text = tablesList[pos].name
        holder.tableProducer.text = tablesList[pos].producer
        holder.tablePrice.text = "Rs. ${tablesList[pos].cost}"
        Picasso.get().load(tablesList[pos].productImages).into(holder.tableImage)
//        holder.tableRating.rating = tablesList[pos].rating!!.toFloat()
    }

    inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tableImage: ImageView = itemView.findViewById(R.id.img_item_table)
        val tableName: TextView = itemView.findViewById(R.id.txt_name_item_table)
        val tableProducer: TextView = itemView.findViewById(R.id.txt_producer_item_table)
        val tablePrice: TextView = itemView.findViewById(R.id.txt_price_item_table)
//        val tableRating: RatingBar = itemView.findViewById(R.id.rb_rating_item_table)

        init {
            itemView.setOnClickListener{
                onProductClickListener.let { onProductClickListener.onProductClicked(adapterPosition) }
            }
        }
    }
}