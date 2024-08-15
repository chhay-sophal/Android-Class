package com.example.myapplication.product_module.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.services.ProductService
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = mutableStateListOf<Product>()
    val products: List<Product>
        get() = _products
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    private val apiService = ProductService.getInstance()

    private var currentPage = 1
    private var isLastPage = false

    fun fetchProducts(page: Int = 1, limit: Int = 10) {
        viewModelScope.launch {
            if (isLastPage) return@launch

            isLoading = true
            try {
                val response = apiService.getProducts(page = page, limit = limit)
                if (response.products.isNotEmpty()) {
                    if (page == 1) {
                        _products.clear()
                    }
                    _products.addAll(response.products)
                    currentPage++
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
}