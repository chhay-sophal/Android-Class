package com.firstapp.jetpack1.myOwnAPI

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//const val originalUrl = "http://10.0.2.2:8080/aubwed/?key=d033e22ae348aeb5660fc2140aec35850c4da997"
private const val PRODUCT_API_BASE_URL = "http://10.0.2.2"


interface ProductAPIService {
    @GET("product/api.php")
    suspend fun getProductList(
        @Query("key") apiKey: String = "d033e22ae348aeb5660fc2140aec35850c4da997",
        @Query("result") result: Int,
        @Query("page") page: Int
    ): List<ProductModel>
    @FormUrlEncoded
    @POST("aubwed/delete.php")
    suspend fun deletedProduct(
        @Query("key") apiKey: String = "d033e22ae348aeb5660fc2140aec35850c4da997",
        @Field("pid") pid: Int?,
    ): String


    @FormUrlEncoded
    @POST("aubwed/add.php")
    suspend fun insertProduct(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997"
    ): String

    companion object {
        private var apiService: ProductAPIService? = null
        fun getInstance(): ProductAPIService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(PRODUCT_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ProductAPIService::class.java)
            }
            return apiService!!
        }
    }
}