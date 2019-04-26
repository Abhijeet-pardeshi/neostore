package com.neosoft.neostoreapp.view.fragment


import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast

import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.database.Address
import com.neosoft.neostoreapp.database.AddressListViewModel
import com.neosoft.neostoreapp.utils.Constants
import com.neosoft.neostoreapp.view.adapter.AddressItemAdapter
import com.neosoft.neostoreapp.view.adapter.CartItemAdapter
import kotlinx.android.synthetic.main.fragment_address.*

class AddressFragment : Fragment(), AddressItemAdapter.OnDeleteClickListener {

    lateinit var addressViewModel: AddressListViewModel
    lateinit var arrAddress: ArrayList<Address>
    lateinit var sharedPreferences: SharedPreferences
    private var userEmail: String? = null
    lateinit var addressAdapter: AddressItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressViewModel = ViewModelProviders.of(this).get(AddressListViewModel::class.java)
        arrAddress = arrayListOf()
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        userEmail = sharedPreferences.getString(Constants.USER_EMAIL, "")
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.address, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        hideSearchAction(menu)
    }

    private fun hideSearchAction(menu: Menu?) {
        menu?.findItem(R.id.action_search)?.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_add -> {
                val addAddressFragment = AddAddressFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.container, addAddressFragment)?.addToBackStack(null)
                    ?.commit()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getAddressByUserEmail(userEmail)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addressAdapter = AddressItemAdapter(context!!, arrAddress)
        addressAdapter.setDeleteClickListener(this)
        rv_addresses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = addressAdapter
        }

        btn_place_order.setOnClickListener {
            placeOrder()
        }
    }

    private fun getAddressByUserEmail(userEmail: String?) {
        arrAddress = addressViewModel.getAddressesByUserEmail(userEmail.toString())
    }

    override fun ondeleteClicked(pos: Int) {
        val address = arrAddress[pos]
        val id = arrAddress[pos].id
        id?.let { addressViewModel.deleteAddressById(it) }
//
//        if (pos == addressAdapter.checkedPos ){
//            if (!arrAddress.isNullOrEmpty()){
//                addressAdapter.checkedPos = pos
//                Log.d("Pos", addressAdapter.checkedPos.toString())
//                addressAdapter.notifyItemChanged(pos)
//            }
//        }
        arrAddress.remove(address)
        addressAdapter.notifyItemRemoved(pos)
    }

    private fun placeOrder() {
        Log.d("CheckedPos", "${addressAdapter.checkedPos}")

        if (addressAdapter.checkedPos == -1) {
            Toast.makeText(context, "Select the address before you proceed", Toast.LENGTH_SHORT).show()
        }
    }
}
