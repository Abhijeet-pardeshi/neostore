package com.neosoft.neostoreapp.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class AddressListViewModel(application: Application) : AndroidViewModel(application) {
    var addressRepository: AddressRepository = AddressRepository(application)

    fun getAllAddresses(): ArrayList<Address> {
        return addressRepository.getAllAddresses()
    }

    fun saveAddress(address: Address){
        addressRepository.saveAddress(address)
    }
}