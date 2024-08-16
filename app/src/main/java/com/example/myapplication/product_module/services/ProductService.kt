package com.example.myapplication.product_module.services

import com.example.myapplication.product_module.models.CreateProductRequest
import com.example.myapplication.product_module.models.ApiResponse
import com.example.myapplication.product_module.models.ProductResponse
import com.example.myapplication.product_module.models.UpdateProductRequest
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

//const val originalURL = "http://10.0.2.2:8080/android_class/product/"
const val PRODUCT_BASE_URL = "http://10.0.2.2"
const val API_KEY = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z"
interface ProductService {

    @GET("product/api.php")
    suspend fun getProducts(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("search") search: String = "",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    @POST("product/api.php")
    suspend fun createProduct(
        @Query("apikey") apiKey: String = API_KEY,
        @Body product: CreateProductRequest
    ): ApiResponse

    @PUT("product/api.php")
    suspend fun updateProduct(
        @Query("apikey") apiKey: String = API_KEY,
        @Body product: UpdateProductRequest
    ): ApiResponse

    @DELETE("product/api.php")
    suspend fun deleteProduct(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("pid") pid: String
    ): ApiResponse

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