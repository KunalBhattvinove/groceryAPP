package com.example.grocerryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grocerryapp.model.GroceryItems

@Database(entities = [GroceryItems::class], version = 1)
abstract class GroceryDatabase : RoomDatabase(){

    abstract fun getGroceryDao() : GroceryDao

    companion object
    {
        private var dbInstance: GroceryDatabase? = null
        fun getDatabaseInstance(context: Context): GroceryDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    "fake_db"
                )
                    .build()
            }
            return dbInstance!!
        }


    }
}