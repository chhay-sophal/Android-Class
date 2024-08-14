package com.example.myapplication.themoviedb_module

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheMovieDetailScreen(movieId: Long, vm: TheMovieViewModel, navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(movieId) {
        vm.getMovieDetail(movieId)
    }

    val movieDetail by remember { mutableStateOf(vm.movieDetail) }
    val isLoading by remember { mutableStateOf(vm.isLoading) }
    val errorMessage by remember { mutableStateOf(vm.errorMessage) }

    TheMovieTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(movieDetail?.title ?: "Movie Detail") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        // Sort Button
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sort),
                                contentDescription = "Sort",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Light/Dark Mode Button
                        IconButton(onClick = { isDarkMode = !isDarkMode }) {
                            Icon(
                                painter = painterResource(id = if (isDarkMode) R.drawable.sun else R.drawable.moon),
                                contentDescription = "Toggle Mode",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator()
                    }
                    errorMessage.isNotEmpty() -> {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    movieDetail != null -> {
                        Column {
                            AsyncImage(
                                model = movieDetail!!.posterPath,
                                contentDescription = "Movie Poster",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(bottom = 16.dp)
                            )
                            Text(
                                text = movieDetail?.title ?: "",
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Release Date: ${movieDetail?.releaseDate ?: ""}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Overview: ${movieDetail?.overview ?: ""}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}