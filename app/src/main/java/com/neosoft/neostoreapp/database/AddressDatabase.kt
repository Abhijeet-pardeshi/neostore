package com.neosoft.neostoreapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Address::class], version = 1)
abstract class AddressDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    companion object {
        var instance: AddressDatabase? = null

        fun getInstance(context: Context): AddressDatabase {
            if (instance == null) {
                synchronized(AddressDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AddressDatabase::class.java,
                        "addresses_database"
                    ).build()
                }
            }

            return instance as AddressDatabase
        }
    }
}