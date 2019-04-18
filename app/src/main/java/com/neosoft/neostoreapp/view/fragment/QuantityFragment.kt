package com.neosoft.neostoreapp.view.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.TextView
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.utils.isNull
import kotlinx.android.synthetic.main.fragment_quantity.*
import kotlinx.android.synthetic.main.fragment_quantity.view.*

class QuantityFragment: DialogFragment(){

    interface OnQuantitySubmitListener{
        fun onQuantitySubmitted(count: Int)
    }

    lateinit var onQuantitySubmitListener: OnQuantitySubmitListener

    fun setOnQuantityListener(onQuantitySubmitListener: OnQuantitySubmitListener){
        this.onQuantitySubmitListener = onQuantitySubmitListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quantity,container,false)

        view.btn_submit_quantity_fragment.setOnClickListener {
            if (edt_select_quantity_fragment.text.toString().isNotEmpty()){
                onQuantitySubmitListener.onQuantitySubmitted(edt_select_quantity_fragment.text.toString().toInt())
                dismiss()
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()

        val window = dialog.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = 0.90f
        windowParams?.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window?.attributes = windowParams
    }
}