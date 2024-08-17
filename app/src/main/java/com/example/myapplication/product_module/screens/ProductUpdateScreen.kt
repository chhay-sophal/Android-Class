package com.example.myapplication.product_module.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.product_module.models.Product
import com.example.myapplication.product_module.models.UpdateProductRequest
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
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Update Product",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    OutlinedTextField(
                        value = title,
                        onValueChange = { newValue -> title = newValue },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = body,
                        onValueChange = { newValue -> body = newValue },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 3
                    )
                    OutlinedTextField(
                        value = qty,
                        onValueChange = { newValue -> qty = newValue },
                        label = { Text("Quantity") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = price,
                        onValueChange = { newValue -> price = newValue },
                        label = { Text("Price") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = image,
                        onValueChange = { newValue -> image = newValue },
                        label = { Text("Image URL") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = category,
                        onValueChange = { newValue -> category = newValue },
                        label = { Text("Category") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            val productData = UpdateProductRequest(product.pid, title, body, qty, price, image, category)
                            viewModel.updateProduct(productData)
                            if (viewModel.errorMessage.isEmpty()) {
                                onProductUpdated()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("Update")
                    }

                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    if (viewModel.errorMessage.isNotEmpty()) {
                        Text(
                            text = viewModel.errorMessage,
                            color = Color.Red,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}
