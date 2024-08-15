package com.example.myapplication.product_module.services

import com.example.myapplication.product_module.models.ProductResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val originalURL = "http://10.0.2.2:8080/android_class/product/"
const val PRODUCT_BASE_URL = "http://10.0.2.2"

interface ProductService {

    @GET("product/api.php")
    suspend fun getProducts(
        @Query("apikey") apiKey: String = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    companion object {
        private var apiService: ProductService? = null
        fun getInstance(): ProductService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(PRODUCT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ProductService::class.java)
            }
            return apiService!!
        }
    }
}