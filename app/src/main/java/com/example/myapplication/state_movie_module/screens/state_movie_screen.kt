package com.example.myapplication.state_movie_module.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.state_movie_module.viewmodels.StateMovieViewModel

@Composable
fun StateMovieScreen(navController: NavController, movieVM: StateMovieViewModel = viewModel()) {
    Scaffold(
        topBar = {
            ComposeTopBar(navController, movieVM)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
//                Row {
//                    Spacer(modifier = Modifier.weight(1f))
//                    Button(onClick = {
//                        movieVM.sortMoviesByTitle()
//                    }) {
//                        if (!movieVM.sortedAZ.value) {
//                            Text(text = "Sort By Title Ascending")
//                        } else {
//                            Text(text = "Sort By Title Descending")
//                        }
//                    }
//                }
                LazyColumn() {
                    items(movieVM.movieList.value.size) { index ->
//                        val movie = movieVM.movieList.value[index]
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween
                        ) {
                            Text(
                                movieVM.movieList.value[index].name,
                                modifier = Modifier.padding(10.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = {
                                movieVM.toggleItemToCart(movieVM.movieList.value[index])
                            }) {
                                Icon(
                                    imageVector = if (movieVM.isInCart(movieVM.movieList.value[index])) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                                    contentDescription = "Toggle Cart"
                                )
                            }
                            IconButton(onClick = {
//                                movieVM.removeMovie(index)
                            }) {
                                Icon(Icons.Default.Close, contentDescription = null)
                            }
                        }

                        AsyncImage(
                            modifier = Modifier
                                .size(width = 500.dp, height = 300.dp)
                                .fillParentMaxWidth()
                                .clickable {
                                    movieVM.selectedMovie.value = movieVM.movieList.value[index]
                                    navController.navigate("detail")
                                },
                            model = movieVM.movieList.value[index].image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeTopBar(nc: NavController, movieVM: StateMovieViewModel = viewModel()) {
    TopAppBar(
        title = { Text(text = "X-MOVIES") },
        colors = topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            BadgedBox(
                badge = {
                    Badge {
                        Text(text = movieVM.cartItems.value.size.toString())
                    }
                }
            ) {
                IconButton(onClick = { nc.navigate("cart") }, modifier = Modifier.size(26.dp)) {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.List, contentDescription = null)
            }
        }
    )
}