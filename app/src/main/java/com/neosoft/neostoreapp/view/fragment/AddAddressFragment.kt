package com.neosoft.neostoreapp.view.fragment


import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.neosoft.neostoreapp.R
import com.neosoft.neostoreapp.database.Address
import com.neosoft.neostoreapp.database.AddressListViewModel
import com.neosoft.neostoreapp.utils.Constants


class AddAddressFragment : Fragment() {
    lateinit var addressListViewModel: AddressListViewModel
    lateinit var arrAddress: ArrayList<Address>
    var accessToken: String? = null
    lateinit var sharedPreferences: SharedPreferences

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

        addressListViewModel.saveAddress(
            Address(
                "122332324",
                "NeoSoft Technologies Mumbai",
                "Opposite to Parel Depot, Sayani Road",
                "Mumbai",
                "100023",
                "Maharashtra",
                "INDIA"
            )
        )

        getAddresses()
    }

    private fun getAddresses() {
        arrAddress = addressListViewModel.getAllAddresses()

        arrAddress.forEach { address ->
            Log.d("Address", "$address")
        }
    }
}
