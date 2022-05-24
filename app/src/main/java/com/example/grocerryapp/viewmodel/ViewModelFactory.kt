package com.example.grocerryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocerryapp.repository.GroceryRepo

class ViewModelFactory(private val repo: GroceryRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroceryViewModel(repo) as T
    }
}