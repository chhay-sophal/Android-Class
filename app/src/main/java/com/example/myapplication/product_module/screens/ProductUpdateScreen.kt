package com.example.myapplication.product_module.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.product_module.models.UpdateProductRequest
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductUpdateScreen(
    viewModel: ProductViewModel,
    product: Product,
    onProductUpdated: () -> Unit,
    onBackPressed: () -> Unit
) {
    var title by remember { mutableStateOf(product.title) }
    var body by remember { mutableStateOf(product.body) }
    var qty by remember { mutableStateOf(product.qty) }
    var price by remember { mutableStateOf(product.price) }
    var image by remember { mutableStateOf(product.image) }
    var category by remember { mutableStateOf(product.category) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = product.title) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = title,
                    onValueChange = { newValue -> title = newValue },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = body,
                    onValueChange = { newValue -> body = newValue },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = qty,
                    onValueChange = { newValue -> qty = newValue },
                    label = { Text("Quantity") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = price,
                    onValueChange = { newValue -> price = newValue },
                    label = { Text("Price") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = image,
                    onValueChange = { newValue -> image = newValue },
                    label = { Text("Image URL") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = category,
                    onValueChange = { newValue -> category = newValue },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val productData = UpdateProductRequest(product.pid ,title, body, qty, price, image, category)
                    viewModel.updateProduct(productData)
                    if (viewModel.errorMessage.isEmpty()) {
                        onProductUpdated()
                    }
                }) {
                    Text("Update Product")
                }

                if (viewModel.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
                if (viewModel.errorMessage.isNotEmpty()) {
                    Text(viewModel.errorMessage, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }
}
