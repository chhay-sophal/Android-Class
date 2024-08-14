package com.example.myapplication.themoviedb_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheMovieScreen(
    vm: TheMovieViewModel,
    nv: NavController,
    isDarkMode: Boolean,
    toggleDarkMode: () -> Unit
) {
    var isGridView by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var sortOption by remember { mutableStateOf("popularity.desc") }

    LaunchedEffect(sortOption) {
        vm.getMovies(sortBy = sortOption)
    }

    TheMovieTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Movies") },
                    actions = {
                        // Sort Button
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sort),
                                contentDescription = "Sort",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Sort Menu
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Sort by Popularity") },
                                onClick = {
                                    sortOption = "popularity.desc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by Release Date") },
                                onClick = {
                                    sortOption = "release_date.desc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by Vote Average") },
                                onClick = {
                                    sortOption = "vote_average.desc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by Popularity (Asc)") },
                                onClick = {
                                    sortOption = "popularity.asc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by Release Date (Asc)") },
                                onClick = {
                                    sortOption = "release_date.asc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by Vote Average (Asc)") },
                                onClick = {
                                    sortOption = "vote_average.asc"
                                    vm.getMovies(sortBy = sortOption)
                                    expanded = false
                                }
                            )
                        }

                        // Grid/List Button
                        IconButton(onClick = { isGridView = !isGridView }) {
                            Icon(
                                painter = painterResource(id = if(isGridView) R.drawable.list else R.drawable.grid),
                                contentDescription = "Toggle View",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Light/Dark Mode Button
                        IconButton(onClick = { toggleDarkMode() }) {
                            Icon(
                                painter = painterResource(id = if(isDarkMode) R.drawable.sun else R.drawable.moon), // Use appropriate icon
                                contentDescription = "Toggle Mode",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Search Button
                        IconButton(onClick = { /* Handle search */ }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search"
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
                TheMovieBody(vm, isGridView, nv)
            }
        }
    }
}

@Composable
fun TheMovieBody(vm: TheMovieViewModel, isGridView: Boolean, nv: NavController) {
    when {
        vm.isLoading && vm.movies.isEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        vm.errorMessage.isNotEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = vm.errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        else -> {
            if (isGridView) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(vm.movies.size) { index ->
                        GridMovieItem(item = vm.movies[index], nv)
                        if (index == vm.movies.size - 1) {
                            LaunchedEffect(Unit) {
                                vm.loadNextPage()
                            }
                        }
                    }
                    if (vm.isLoading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(vm.movies.size) { index ->
                        ListMovieItem(item = vm.movies[index], nv)
                        if (index == vm.movies.size - 1) {
                            LaunchedEffect(Unit) {
                                vm.loadNextPage()
                            }
                        }
                    }
                    if (vm.isLoading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridMovieItem(item: Result, nv: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(8.dp))
            .clickable {
                nv.navigate("movie_detail/${item.id}")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = item.fullPosterPath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 20.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(end = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = item.releaseDate,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(5.dp))
            CircularVote(voteAverage = item.voteAverage)
        }
    }
}

@Composable
fun ListMovieItem(item: Result, nv: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(8.dp))
            .clickable {
                nv.navigate("movie_detail/${item.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.fullPosterPath,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp, 100.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 20.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(end = 16.dp)
                )
            }
            Text(
                text = item.releaseDate,
                style = MaterialTheme.typography.bodyMedium
            )
            CircularVote(voteAverage = item.voteAverage)
        }
    }
}

@Composable
fun CircularVote(voteAverage: Double) {
    val percentage = (voteAverage * 10).toInt()
    val color = when {
        percentage >= 75 -> Color.Green
        percentage >= 50 -> Color.Yellow
        else -> Color.Red
    }

    Box( contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
        ) {
            val sweepAngle = 360 * (percentage / 100f)


            drawCircle(
                color = Color.DarkGray,
                radius = size.minDimension / 2,
                center = center
            )

            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 8f)
            )
            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 8f)
            )

        }
        Text(
            text = "$percentage%",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Composable
fun TheMovieTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorScheme(
            background = Color.DarkGray,
            onBackground = Color.LightGray,
            surface = Color.DarkGray,
            onSurface = Color.LightGray,
        )
    } else {
        lightColorScheme(
            background = Color.White,
            onBackground = Color.DarkGray,
            surface = Color.White,
            onSurface = Color.DarkGray,
        )
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
