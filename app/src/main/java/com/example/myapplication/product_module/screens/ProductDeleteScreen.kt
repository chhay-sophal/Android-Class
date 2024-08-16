package com.example.myapplication.product_module.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@Composable
fun ProductDeleteScreen(viewModel: ProductViewModel, product: Product, onProductDeleted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Are you sure you want to delete ${product.title}?", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
//            Button(onClick = {
//                viewModel.deleteProduct(product.pid)
//                onProductDeleted()
//            }) {
//                Text("Delete")
//            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { onProductDeleted() }) {
                Text("Cancel")
            }
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
        if (viewModel.errorMessage.isNotEmpty()) {
            Text(viewModel.errorMessage, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}
