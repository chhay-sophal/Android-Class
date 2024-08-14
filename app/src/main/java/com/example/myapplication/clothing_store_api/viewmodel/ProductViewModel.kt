package com.example.myapplication.clothing_store_api.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.clothing_store_api.api_service.ProductsAPIService
import com.example.myapplication.clothing_store_api.models.ProductsItem
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = mutableStateListOf<ProductsItem>()
    private val _selectedProduct = mutableStateOf<ProductsItem?>(null)
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val productsList: List<ProductsItem> get() = _products
    var selectedProduct: ProductsItem?
        get() = _selectedProduct.value
        private set(selectedProductValue) {
            _selectedProduct.value = selectedProductValue
        }

    fun getProducts() {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductsAPIService.getInstance()
            errorMessage = try {
                _products.clear()
                _products.addAll(apiService.getProducts())
                ""
            } catch (e: Exception) {
                "Failed to fetch products: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun setSelectedProductValue(product: ProductsItem) {
        selectedProduct = product
    }

    fun sortProductsByPriceLowToHigh() {
        val sortedList = _products.sortedBy { it.price }
        _products.clear()
        _products.addAll(sortedList)
    }

    fun sortProductsByPriceHighToLow() {
        val sortedList = _products.sortedByDescending { it.price }
        _products.clear()
        _products.addAll(sortedList)
    }

}

