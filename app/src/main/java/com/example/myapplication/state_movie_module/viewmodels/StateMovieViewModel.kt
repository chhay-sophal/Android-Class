package com.example.myapplication.state_movie_module.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.state_movie_module.models.StateMovieModel
import com.example.myapplication.state_movie_module.models.stateMovieList1

class StateMovieViewModel: ViewModel() {
//    var movieList: MutableList<MovieModel> = movieList1.toMutableList()
val movieList: MutableState<List<StateMovieModel>> = mutableStateOf(stateMovieList1)
    val selectedMovie: MutableState<StateMovieModel> = mutableStateOf(StateMovieModel())
    var sortedAZ: MutableState<Boolean> = mutableStateOf(false)

    fun sortMoviesByTitle() {
        sortedAZ.value = !sortedAZ.value
        if (sortedAZ.value) {
            movieList.value = movieList.value.sortedBy { it.name }
        } else {
            movieList.value = movieList.value.sortedByDescending { it.name }
        }
    }

    fun removeMovie(index: Int) {
        if (index >= 0 && index < movieList.value.size) {
            val newMutableList = movieList.value.toMutableList()
            newMutableList.removeAt(index)
            movieList.value = newMutableList
        }
    }
}