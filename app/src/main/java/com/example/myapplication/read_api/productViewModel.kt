package com.firstapp.jetpack1.myOwnAPI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductAPIViewModel : ViewModel() {
    private val _productList = mutableStateListOf<ProductModel>()
    var errorMessage by mutableStateOf("")
    var successMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    val productList: List<ProductModel>
        get() = _productList

    fun getProductList(results: Int = 25) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                _productList.clear()
                _productList.addAll(apiService.getProductList(result = results, page = 1))
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun insertProduct(product: ProductModel) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                val response: String = apiService.insertProduct(
                    title = product.title,
                    body = product.body,
                    price = product.price,
                    qty = product.qty,
                    category = product.category,
                    image = product.image
                )
                if (response == "success") {
                    successMessage = response
                    getProductList() // Refresh the product list after insertion
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }
    fun deleteProduct(pid: Int?) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                val response: String = apiService.deletedProduct(
                    pid = pid
                )
                if (response == "success") {
                    successMessage = response
                    getProductList() // Refresh the product list after insertion
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }
}
