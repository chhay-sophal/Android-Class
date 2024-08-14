package com.example.myapplication.themoviedb_module

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//val originalUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=1&api_key=f7dabf39cb7c0323fb4658741c5cb24d"
//val TheMovieDB_BASE_URL = "https://api.themoviedb.org/3/discover/"
val TheMovieDB_BASE_URL = "https://api.themoviedb.org/3/"

interface TheMovieService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "f7dabf39cb7c0323fb4658741c5cb24d"
    ): TheMovie

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = "f7dabf39cb7c0323fb4658741c5cb24d"
    ): Result

    companion object {
        private var apiService: TheMovieService? = null
        fun getInstance(): TheMovieService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(TheMovieDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(TheMovieService::class.java)
            }
            return apiService!!
        }
    }
}