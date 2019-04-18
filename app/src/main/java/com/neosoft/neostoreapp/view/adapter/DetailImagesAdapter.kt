package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.model.response.DetailImagesResponse
import com.squareup.picasso.Picasso

class DetailImagesAdapter(var imagesList: ArrayList<DetailImagesResponse>, var context: Context) :
    RecyclerView.Adapter<DetailImagesAdapter.ImageViewHolder>() {

    var selectedPos = 0

    interface OnImageClickListener {
        fun onImageCliked(pos: Int)
    }

    lateinit var onImageClickListener: OnImageClickListener

    fun setImageClickListener(onImageClickListener: OnImageClickListener) {
        this.onImageClickListener = onImageClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, pos: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image_detail, p0, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, pos: Int) {



        Picasso.get().load(imagesList[pos].image).into(holder.detailImage)
        holder.itemView.isSelected = (selectedPos == pos)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var detailImage: ImageView = itemView.findViewById(R.id.iv_small_pic_detail)

        init {

            detailImage.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = layoutPosition
                notifyItemChanged(selectedPos)

                onImageClickListener.onImageCliked(adapterPosition)
            }
        }
    }

}