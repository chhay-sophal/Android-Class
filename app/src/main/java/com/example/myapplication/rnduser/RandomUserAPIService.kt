package com.example.myapplication.rnduser

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val RNDUSR_BASE_URL = "https://randomuser.me/"

interface RandomUsersAPIService {
    @GET("api")
    suspend fun getUsers(@Query("results") results: Int = 20): User
    companion object {
        private var apiService: RandomUsersAPIService? = null
        fun getInstance(): RandomUsersAPIService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(RNDUSR_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(RandomUsersAPIService::class.java)
            }
            return apiService!!
        }
    }
}