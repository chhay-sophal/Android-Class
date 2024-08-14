package com.example.myapplication.themoviedb_module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(showSystemUi = true)
@Composable
fun StateTheMovie(){
    val navController = rememberNavController()

    val movieVM: TheMovieViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            TheMovieScreen(movieVM, navController)
        }

        composable(route = "movie_detail/{movieId}") {backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toLong() ?: 0L
            val selectedMovie = movieVM.movies.find { it.id == movieId }
            if (selectedMovie != null) {
                TheMovieDetailScreen(selectedMovie, navController)
            }
        }
    }
}