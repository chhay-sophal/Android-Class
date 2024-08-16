package com.example.myapplication.product_module.services

import com.example.myapplication.product_module.models.CreateProductResponse
import com.example.myapplication.product_module.models.ProductResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

const val originalURL = "http://10.0.2.2:8080/android_class/product/"
const val PRODUCT_BASE_URL = "http://10.0.2.2"
const val API_KEY = "d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z"
interface ProductService {

    @GET("product/api.php")
    suspend fun getProducts(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    @FormUrlEncoded
    @POST("product/edit.php")
    suspend fun createProduct(
        @Query("apikey") apiKey: String = API_KEY,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("image") image: String,
        @Field("category") category: String
    ): CreateProductResponse

    @FormUrlEncoded
    @PUT("product/edit.php")
    suspend fun updateProduct(
        @Query("apikey") apiKey: String,
        @Field("pid") pid: Int,
        @Field("title") title: String? = null,
        @Field("body") body: String? = null,
        @Field("qty") qty: String? = null,
        @Field("price") price: String? = null,
        @Field("image") image: String? = null,
        @Field("category") category: String? = null
    ): ProductResponse

    @FormUrlEncoded
    @DELETE("product/edit.php")
    suspend fun deleteProduct(
        @Query("apikey") apiKey: String,
        @Field("pid") pid: Int
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