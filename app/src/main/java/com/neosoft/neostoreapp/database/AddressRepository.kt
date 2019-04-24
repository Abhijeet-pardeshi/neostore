package com.neosoft.neostoreapp.database

import android.app.Application
import android.os.AsyncTask

class AddressRepository(application: Application) {

    private var addressDao: AddressDao

    init {
        val database: AddressDatabase? = AddressDatabase.getInstance(application.applicationContext)

        addressDao = database!!.addressDao()
    }

    fun getAllAddresses(): ArrayList<Address> {
        val getAddressAsyncTask = GetAddressAsyncTask(addressDao).execute()
        return getAddressAsyncTask.get()
    }

    fun saveAddress(address: Address) {
//        val saveAddressAsyncTask =
        SaveAddressAsyncTask(addressDao).execute(address)
    }

    companion object {
        private class GetAddressAsyncTask(var addressDao: AddressDao) : AsyncTask<Unit, Unit, ArrayList<Address>>() {
            override fun doInBackground(vararg params: Unit?): ArrayList<Address> {
                return addressDao.getAllAddresses() as ArrayList<Address>
            }
        }

        private class SaveAddressAsyncTask(var addressDao: AddressDao) : AsyncTask<Address, Unit, Unit>() {
            override fun doInBackground(vararg params: Address?) {
                params[0]?.let {
                    addressDao.saveAddress(it)
                }
            }
        }
    }
}