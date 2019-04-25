package com.neosoft.neostoreapp.view.fragment


import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast

import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.database.Address
import com.neosoft.neostoreapp.database.AddressListViewModel
import com.neosoft.neostoreapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_add_address.*


class AddAddressFragment : Fragment() {
    lateinit var addressListViewModel: AddressListViewModel
    lateinit var arrAddress: ArrayList<Address>
    var accessToken: String? = null
    var userEmail: String? = null
    lateinit var sharedPreferences: SharedPreferences

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater?.inflate(R.menu.address, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onPrepareOptionsMenu(menu: Menu?) {
//        super.onPrepareOptionsMenu(menu)
//        menu?.findItem(R.id.action_search)?.isVisible = false
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        when (item?.itemId) {
//            R.id.action_add -> Toast.makeText(context, "Add Address", Toast.LENGTH_SHORT).show()
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addressListViewModel = ViewModelProviders.of(this).get(AddressListViewModel::class.java)
        arrAddress = arrayListOf()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = context?.getSharedPreferences(Constants.PREF_NAME, 0)!!
        accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
        userEmail = sharedPreferences.getString(Constants.USER_EMAIL, "")

        btn_save_address.setOnClickListener {
            val city = edt_city_address_fragment.text.toString()
            val landmark = edt_landmark_add_fragment.text.toString()
            val zipCode = edt_zipcode_address_fragment.text.toString()
            val state = edt_state_address_fragment.text.toString()
            val country = edt_country_address_fragment.text.toString()
            val fullAddress = edt_address_add_fragment.text.toString()
            val address = Address(this.userEmail!!, fullAddress, landmark, city, zipCode, state, country)
            saveAddress(address)
//            addressListViewModel.saveAddress(
//                Address(
//                    this.userEmail!!,
//                    "NeoSoft Technologies Mumbai",
//                    "Opposite to Parel Depot, Sayani Road",
//                    "Mumbai",
//                    "100023",
//                    "Maharashtra",
//                    "INDIA"
//                )
//            )
        }
    }

    private fun saveAddress(address: Address) {
        addressListViewModel.saveAddress(address)
        getAddressesByUserEmail(userEmail!!)
    }

    private fun getAddressesByUserEmail(userEmail: String) {
        arrAddress = addressListViewModel.getAddressesByUserEmail(userEmail)

        arrAddress.forEach { address ->
            Log.d("Address", "$address")
        }
    }

//    private fun getAddresses() {
//        arrAddress = addressListViewModel.getAllAddresses()
//
//        arrAddress.forEach { address ->
//            Log.d("Address", "$address")
//        }
//    }
}
