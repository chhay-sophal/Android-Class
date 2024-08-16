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

    fun fetchProducts(page: Int = 1, limit: Int = 20) {
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

    fun createProduct(product: Product) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = apiService.createProduct(
                    title = product.title,
                    body = product.body,
                    qty = product.qty,
                    price = product.price,
                    image = product.image,
                    category = product.category
                )
                if (response.success == true) {
//                    _products.add(response.product)
                    fetchProducts()
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

//    fun updateProduct(
//        pid: Int,
//        title: String? = null,
//        body: String? = null,
//        qty: Int? = null,
//        price: Double? = null,
//        image: String? = null,
//        category: String? = null
//    ) {
//        viewModelScope.launch {
//            isLoading = true
//            try {
//                val response = apiService.updateProduct(
//                    apiKey = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z",
//                    pid = pid,
//                    title = title,
//                    body = body,
//                    qty = qty,
//                    price = price,
//                    image = image,
//                    category = category
//                )
//
//                // Check if response.products is not null and not empty
//                val productsList = response.products
//                if (productsList.isNotEmpty()) {
//                    val index = _products.indexOfFirst { it.pid == pid }
//                    if (index != -1) {
//                        _products[index] = productsList.first()
//                    }
//                } else {
//                    errorMessage = "No products returned from the update."
//                }
//            } catch (e: Exception) {
//                errorMessage = e.message.toString()
//            } finally {
//                isLoading = false
//            }
//        }
//    }

//    fun deleteProduct(pid: Int) {
//        viewModelScope.launch {
//            isLoading = true
//            try {
//                val response = apiService.deleteProduct(
//                    apiKey = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z",
//                    pid = pid
//                )
//                if (response.products.isEmpty()) {
//                    _products.removeAll { it.pid == pid }
//                }
//            } catch (e: Exception) {
//                errorMessage = e.message.toString()
//            } finally {
//                isLoading = false
//            }
//        }
//    }
}