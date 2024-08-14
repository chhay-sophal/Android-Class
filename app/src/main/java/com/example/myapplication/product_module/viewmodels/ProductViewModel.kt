package com.example.myapplication.product_module.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.models.ProductResponse
import com.example.myapplication.product_module.services.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        val productService = ProductService.getInstance()
        val apiKey = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z"
        productService.getProducts(apiKey, page = 1, limit = 10).enqueue(object :
            Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    Log.d("ProductViewModel", "Response Success: ${response.body()}")
                    _products.value = response.body()?.products ?: emptyList()
                } else {
                    Log.e("ProductViewModel", "Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("ProductViewModel", "Request Failed: ${t.message}")
            }
        })
    }
}
