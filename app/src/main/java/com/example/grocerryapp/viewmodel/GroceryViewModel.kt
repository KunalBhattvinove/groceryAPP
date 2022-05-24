package com.example.grocerryapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.grocerryapp.model.GroceryItems
import com.example.grocerryapp.repository.GroceryRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repo:GroceryRepo): ViewModel() {

    fun insert(items:GroceryItems) = GlobalScope.launch{
                    repo.insert(items)
    }

    fun delete(items: GroceryItems) = GlobalScope.launch {
                    repo.delete(items)
    }

    fun getAllItems() = repo.getAllItems()
}