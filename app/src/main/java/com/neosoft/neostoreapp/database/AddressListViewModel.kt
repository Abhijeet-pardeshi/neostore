package com.neosoft.neostoreapp.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class AddressListViewModel(application: Application) : AndroidViewModel(application) {
    var addressRepository: AddressRepository = AddressRepository(application)

    fun getAllAddresses(): ArrayList<Address> {
        return addressRepository.getAllAddresses()
    }

    fun saveAddress(address: Address) {
        addressRepository.saveAddress(address)
    }

    fun getAddressesByUserEmail(userEmail: String): ArrayList<Address> {
        return addressRepository.getAddressesByUserEmail(userEmail)
    }

    fun deleteAddressById(id: Int) {
        return addressRepository.deleteAddressById(id)
    }

}