package com.example.myapplication.state_movie_module.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.state_movie_module.models.stateMovieList1
import com.example.myapplication.state_movie_module.viewmodels.StateMovieViewModel

@Composable
fun StateMovieScreen(navController: NavController, movieVM: StateMovieViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn() {
            items(stateMovieList1.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(
                        stateMovieList1[index].name,
                        modifier = Modifier.padding(10.dp)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }

                AsyncImage(
                    modifier = Modifier
                        .size(width = 500.dp, height = 300.dp)
                        .fillParentMaxWidth()
                        .clickable {
                            movieVM.selectedMovie.value = stateMovieList1[index]
                            navController.navigate("detail")
                        },
                    model = stateMovieList1[index].image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}