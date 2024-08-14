package com.example.myapplication.themoviedb_module

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var isDarkMode by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            TheMovieScreen(
                vm = movieVM,
                nv = navController,
                isDarkMode = isDarkMode,
                toggleDarkMode = { isDarkMode = !isDarkMode }
            )
        }

        composable(route = "movie_detail/{movieId}") {backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toLong() ?: 0L
            val selectedMovie = movieVM.movies.find { it.id == movieId }
            if (selectedMovie != null) {
                TheMovieDetailScreen(
                    selectedMovie = selectedMovie,
                    navController = navController,
                    isDarkMode = isDarkMode,
                    toggleDarkMode = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}