package com.example.myapplication.product_module.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.product_module.models.Product
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val _productList = mutableStateListOf<Product>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val productList: List<Product>
        get() = _productList

    fun getProducts() {
        viewModelScope.launch {
            isLoading = true
            val apiService = 
        }
    }
}