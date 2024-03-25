package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.models.movieList1

@Composable()
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(movieList1.size) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detail/${movieList1[it].name}")
                            },
                        model = movieList1[it].image,
                        contentDescription = null,
                    )
                }
            }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController());
}

@Composable
fun App() {
    MoviesColumn();
}

@Composable
fun MoviesColumn() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(movieList1.size) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    model = movieList1[it].image,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun ComposableText() {
    Text(text = "Hello Android!");
}