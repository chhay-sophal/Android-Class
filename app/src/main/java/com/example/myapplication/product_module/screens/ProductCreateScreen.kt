package com.example.myapplication.product_module.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCreateScreen(viewModel: ProductViewModel, onProductCreated: () -> Unit, onBackPressed: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create a Product")
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

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = body, onValueChange = { body = it }, label = { Text("Description") })
        TextField(value = qty, onValueChange = { qty = it }, label = { Text("Quantity") })
        TextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
        TextField(value = image, onValueChange = { image = it }, label = { Text("Image URL") })
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val product = Product(
                title = title,
                body = body,
                qty = qty,
                price = price,
                image = image,
                category = category,
            )
            viewModel.createProduct(product)
            onProductCreated()
        }) {
            Text("Create Product")
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
        if (viewModel.errorMessage.isNotEmpty()) {
            Text(viewModel.errorMessage, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}
