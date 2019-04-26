package com.neosoft.neostoreapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "addresses_table")
data class Address(
    var userEmail: String,
    var userName: String,
    var fullAddress: String,
    var landmark: String,
    var city: String,
    var zipCode: String,
    var state: String,
    var country: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}