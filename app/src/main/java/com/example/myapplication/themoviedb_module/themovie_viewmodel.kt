package com.example.myapplication.themoviedb_module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TheMovieViewModel: ViewModel() {
    private val _movies = mutableStateListOf<Result>()
    val movies: List<Result>
        get() = _movies
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    private val apiService = TheMovieService.getInstance()

    private var currentPage = 1
    private var isLastPage = false

    fun getMovies(sortBy: String = "popularity.desc", page: Int = 1) {
        viewModelScope.launch {
            if (isLastPage) return@launch

            isLoading = true
            try {
                val response = apiService.getMovies(sortBy = sortBy, page = page)
                if (response.results.isNotEmpty()) {
                    _movies.addAll(response.results)
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

    fun loadNextPage(sortBy: String = "popularity.desc") {
        getMovies(sortBy, currentPage)
    }
}