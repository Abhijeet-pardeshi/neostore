package com.neosoft.neostoreapp.view.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quantity.view.*

class QuantityFragment : DialogFragment() {
    lateinit var dialogView: View
    lateinit var bundle: Bundle

    interface OnQuantitySubmitListener {
        fun onQauntitySubmit(quantity: Int)
    }

    lateinit var onQuantitySubmitListener: OnQuantitySubmitListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogView = inflater.inflate(R.layout.fragment_quantity, container, false)
        loadData()

        dialogView.btn_submit_quantity_fragment.setOnClickListener {
            val quantity = dialogView.edt_select_quantity_fragment.text.toString()
            if (quantity.isNotEmpty()) {
                if (quantity.toInt() > 8) {
                    dialogView.edt_select_quantity_fragment.error = "Quantity must be 1 to 8"
                } else {
                    onQuantitySubmitListener = targetFragment as OnQuantitySubmitListener
                    onQuantitySubmitListener.onQauntitySubmit(quantity.toInt())
                    dialog.dismiss()
                }
            }
        }

        return dialogView
    }

    private fun loadData() {
        bundle = this.arguments!!
        dialogView.txt_title_quantity_fragment.text = bundle.getString(Constants.PRODUCT_TITLE)
        Picasso.get().load(bundle.getString(Constants.PRODUCT_IMG)).into(dialogView.img_product_quantity_fragment)
    }
}