package com.neosoft.neostoreapp.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AddressDao {

    @Insert
    fun saveAddress(address: Address)

    @Query("SELECT * FROM addresses_table")
    fun getAllAddresses(): List<Address>

    @Query("SELECT * FROM addresses_table WHERE userName LIKE :userName")
    fun getAddressesByUser(userName: String): List<Address>
}