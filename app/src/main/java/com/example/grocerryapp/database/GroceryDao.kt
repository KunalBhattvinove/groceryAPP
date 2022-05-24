package com.example.grocerryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.*
import com.example.grocerryapp.model.GroceryItems

@Dao
interface GroceryDao {

    @Insert
    suspend fun insert(item: GroceryItems)

    @Delete
    suspend fun delete(item: GroceryItems)

    @Query("Select * from groceryItem")
    fun getAllItems() : LiveData<List<GroceryItems>>
}