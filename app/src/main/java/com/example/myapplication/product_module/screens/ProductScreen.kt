package com.example.myapplication.product_module.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(productViewModel: ProductViewModel) {
    LaunchedEffect(Unit) {
        productViewModel.fetchProducts()
    }

    val products = productViewModel.products
    val isLoading = productViewModel.isLoading
    val errorMessage = productViewModel.errorMessage

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Products") }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                errorMessage.isNotEmpty() -> {
                    Text(text = errorMessage, color = Color.Red)
                }
                products.isNotEmpty() -> {
                    ProductList(products = products)
                }
                else -> {
                    Text(text = "No products available.")
                }
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn {
        items(products.size) { index ->
            ProductItem(product = products[index])
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Log.d("ProductItem", "Image URL: ${product.image}")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 5.dp)
            .background(Color.LightGray, RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp, Color.DarkGray), RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}