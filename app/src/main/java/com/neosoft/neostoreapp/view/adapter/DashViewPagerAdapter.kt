package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.neosoft.neostoreapp.R

class DashViewPagerAdapter constructor() : PagerAdapter() {
    var context: Context? = null
    var images: IntArray? = null

    constructor(context: Context, images: IntArray) : this() {
        this.context = context
        this.images = images
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj as LinearLayout
    }

    override fun getCount(): Int {
        return images?.size ?: 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_pager_dashboard, container, false)

        val sliderImage = itemView.findViewById<ImageView>(R.id.iv_slider)
        images?.get(position)?.let { sliderImage.setImageResource(it) }

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as LinearLayout)
    }
}