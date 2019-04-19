package com.neosoft.neostoreapp.view.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rating.*
import kotlinx.android.synthetic.main.fragment_rating.view.*

class RatingFragment : DialogFragment() {
    lateinit var dialogView: View
    lateinit var bundle: Bundle

    interface OnRatingSubmitListener {
        fun onRatingSubmit(rating: Float)
    }

    lateinit var onRatingSubmitListener: OnRatingSubmitListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogView = inflater.inflate(R.layout.fragment_rating, container, false)
        loadData()

        dialogView.btn_submit_rating_fragment.setOnClickListener {
            val rating = ratingbar_rating_fragment.rating
            Log.d("Rating", rating.toString())
            onRatingSubmitListener = targetFragment as OnRatingSubmitListener
            onRatingSubmitListener.onRatingSubmit(rating)
            dismiss()
        }
        return dialogView
    }

    private fun loadData() {
        bundle = this.arguments!!
        dialogView.txt_title_rating_fragment.text = bundle.getString(Constants.PRODUCT_TITLE)
        Picasso.get().load(bundle.getString(Constants.PRODUCT_IMG)).into(dialogView.img_product_rating_fragment)
    }
}