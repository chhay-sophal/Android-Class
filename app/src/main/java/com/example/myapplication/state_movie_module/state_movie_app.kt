package com.example.myapplication.state_movie_module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.state_movie_module.screens.StateCartScreen
import com.example.myapplication.state_movie_module.screens.StateMovieDetailsScreen
import com.example.myapplication.state_movie_module.screens.StateMovieScreen
import com.example.myapplication.state_movie_module.viewmodels.StateMovieViewModel

@Preview(showSystemUi = true)
@Composable
fun StateMovieApp(){
    val navController = rememberNavController()

    val movieVM: StateMovieViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            StateMovieScreen(navController = navController, movieVM = movieVM)
        }

        composable(route = "detail") {
            StateMovieDetailsScreen(navController = navController, movieVM = movieVM)
        }

        composable(route = "cart") {
            StateCartScreen(navController = navController, movieVM = movieVM)
        }
    }
}