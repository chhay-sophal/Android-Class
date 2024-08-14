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
    var movieDetail: Result? by mutableStateOf(null)

    fun getMovies(sortBy: String = "popularity.desc") {
        viewModelScope.launch {
            isLoading = true
            try {
                _movies.clear()
                _movies.addAll(apiService.getMovies(sortBy = sortBy).results)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch {
            isLoading = true
            try {
                movieDetail = apiService.getMovieDetail(movieId)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
}