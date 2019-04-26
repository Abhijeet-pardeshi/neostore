package com.neosoft.neostoreapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.database.Address

class AddressItemAdapter(var context: Context, var arrAddresses: ArrayList<Address>) :
    RecyclerView.Adapter<AddressItemAdapter.AddressHolder>() {

    var checkedPos = 0

    interface OnDeleteClickListener {
        fun ondeleteClicked(pos: Int)
    }

    lateinit var onDeleteClickListener: OnDeleteClickListener

    fun setDeleteClickListener(onDeleteClickListener: OnDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AddressHolder {
        val addressView = LayoutInflater.from(context).inflate(R.layout.item_address_fragment, p0, false)
        return AddressHolder(addressView)
    }

    override fun getItemCount(): Int {
        return arrAddresses.size
    }

    override fun onBindViewHolder(holder: AddressHolder, pos: Int) {
        holder.txtUserName.text = arrAddresses[pos].userName
        holder.txtFullAddress.text = arrAddresses[pos].fullAddress
        holder.rbAddressChooser.isChecked = (checkedPos == pos)
    }

    inner class AddressHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtUserName = itemView.findViewById<TextView>(R.id.txt_uname_address_fragment)
        val txtFullAddress = itemView.findViewById<TextView>(R.id.txt_full_address_fragment)
        val ibDeleteAddress = itemView.findViewById<ImageButton>(R.id.ib_remove_address_fragment)
        val rbAddressChooser = itemView.findViewById<RadioButton>(R.id.rb_address_fragment)

        init {
            ibDeleteAddress.setOnClickListener {
                onDeleteClickListener.let {
                    Log.d("Pos", adapterPosition.toString())
                    if (adapterPosition == checkedPos) {
                        Log.d("Change", "Deleted the default address")
                        checkedPos = -1
                    }
                    onDeleteClickListener.ondeleteClicked(adapterPosition)
                }
            }

            rbAddressChooser.setOnClickListener {
                notifyItemChanged(checkedPos)
                checkedPos = layoutPosition
                notifyItemChanged(checkedPos)
            }
        }
    }

}