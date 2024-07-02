package com.example.myapplication.themoviedb_module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheMovieScreen(vm: TheMovieViewModel) {
    LaunchedEffect(Unit) { vm.getMovies() }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("The Movie Database") }, actions = {
                IconButton(onClick = { vm.getMovies() }) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TheMovieBody(vm = vm)
        }
    }
}

@Composable
fun TheMovieBody(vm: TheMovieViewModel) {
    if (vm.isLoading) {
        CircularProgressIndicator()
    } else if (vm.errorMessage.isNotEmpty()) {
        Text(vm.errorMessage)
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vm.movies.size) {
                TheMovieItem(item = vm.movies[it])
            }
        }
    }
}

@Composable
fun TheMovieItem(item: Result) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Gray)
        ) {
            AsyncImage(model = item.fullPosterPath, contentDescription = null)
        }
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = item.title)
            Text(text = item.releaseDate)
            Text(text = item.voteAverage.toString())
        }
    }
}