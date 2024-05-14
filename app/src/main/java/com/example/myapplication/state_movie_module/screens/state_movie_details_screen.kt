package com.example.myapplication.state_movie_module.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.state_movie_module.viewmodels.StateMovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateMovieDetailsScreen(navController: NavController, movieVM: StateMovieViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text(text = "Detail Page") }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.Red)
        ) {
            AsyncImage(
                model = movieVM.selectedMovie.value.image,
                contentDescription = movieVM.selectedMovie.value.name,
                modifier = Modifier.fillMaxSize())
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ComposeTopBar(navController: NavController) {
//    TopAppBar(
//        navigationIcon = {
//            IconButton(onClick = { navController.popBackStack() }) {
//                Icon(Icons.Default.ArrowBack, contentDescription = null)
//            }
//        },
//        title = { Text(text = "Detail Page") }
//    )
//}