package com.example.myapplication.clothing_store_api.api_service

import com.example.myapplication.clothing_store_api.models.Products
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val PRODUCTS_BASE_URL = "https://fakestoreapi.com/"

interface ProductsAPIService {
    @GET("products")
    suspend fun getProducts(): Products

    companion object {
        private var apiService: ProductsAPIService? = null
        fun getInstance(): ProductsAPIService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(PRODUCTS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ProductsAPIService::class.java)
            }
            return apiService!!
        }
    }
}