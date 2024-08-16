package com.example.myapplication.product_module.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductViewModel,
    productId: Int,
    onNavigateToUpdate: (Product) -> Unit,
    onNavigateToDelete: (Product) -> Unit,
    onBackPressed: () -> Unit
) {
    val product = viewModel.products.find { it.pid.toInt() == productId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (product != null) {
                        Text(text = product.title)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) { // Back button
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (product != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = product.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Description: ${product.body}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Quantity: ${product.qty}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Price: \$${product.price}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Category: ${product.category}")
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Button(
                            onClick = { onNavigateToUpdate(product) },
                            modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
                        ) {
                            Text("Update Product")
                        }
                        Button(
                            onClick = { onNavigateToDelete(product) },
                            modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
                        ) {
                            Text("Delete Product")
                        }
                    }
                }
            } else {
                Text(
                    text = "Product not found",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
