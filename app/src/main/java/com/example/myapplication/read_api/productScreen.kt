package com.firstapp.jetpack1.myOwnAPI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAPIScreen() {
    val navController = rememberNavController()
    val productViewModel: ProductAPIViewModel = viewModel()
    NavHost(navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen(navController, productViewModel)
        }
        composable("add_product") {
            InsertProductScreen(navController, productViewModel)
        }
        composable("delete/{id}",arguments = listOf(navArgument("id"){
            type = NavType.StringType
        })) { backStackEntry ->
            val pid = backStackEntry.arguments?.getString("id")?.toInt()
            productViewModel.deleteProduct(pid)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, productViewModel: ProductAPIViewModel) {
    LaunchedEffect(Unit) {
        productViewModel.getProductList()
    }

    val products by remember { mutableStateOf(productViewModel.productList) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products List", color = Color.Black) },
                actions = {
                    IconButton(onClick = { productViewModel.getProductList() }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { /* Handle sorting */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Sort",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { navController.navigate("add_product") }) {
                        Icon(
                            Icons.Default.AddCircle,
                            contentDescription = "Add",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when {
                    productViewModel.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    productViewModel.errorMessage.isNotEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = productViewModel.errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                    }
                    else -> {
                        LazyColumn {
                            items(products) { product ->
                                ProductItem(navController,product)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ProductItem(navController: NavController,product: ProductModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Load product image
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )

            // Product details
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Price: $${product.price}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.body,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = { navController.navigate("delete/${product.pid}") }) {
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    }
}





