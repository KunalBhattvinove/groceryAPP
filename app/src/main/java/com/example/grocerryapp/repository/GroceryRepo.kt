package com.example.grocerryapp.repository

import com.example.grocerryapp.database.GroceryDatabase
import com.example.grocerryapp.model.GroceryItems

class GroceryRepo(private val db:GroceryDatabase) {

    suspend fun insert(items: GroceryItems) = db.getGroceryDao().insert(items)

    suspend fun delete(items: GroceryItems) = db.getGroceryDao().delete(items)

    fun getAllItems() = db.getGroceryDao().getAllItems()
}