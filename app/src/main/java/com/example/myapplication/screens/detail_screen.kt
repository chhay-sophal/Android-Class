package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.MainActivity
import com.example.myapplication.models.MovieModel
import com.example.myapplication.models.movieList1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, selectedMovieModel: MovieModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Details Page") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column() {
                Text(
                    text = selectedMovieModel.name,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(10.dp, 20.dp)
                )
                AsyncImage(
                    model = selectedMovieModel.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = selectedMovieModel.des,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(10.dp, 20.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    val selectedMovie = movieList1.first {it.name == "Spider-Man: Into the Spider-Verse"}
    DetailScreen(navController = rememberNavController(), selectedMovieModel = selectedMovie)
}
