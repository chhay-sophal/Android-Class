package com.firstapp.jetpack1.myOwnAPI

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InsertProductScreen(navController: NavController, vm: ProductAPIViewModel) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("0.0") }
    var qty by remember { mutableStateOf("0") }
    var imageUrl by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                placeholder = { Text("Enter Title") },
                leadingIcon = {
                    Icon(Icons.Default.TextFields, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(100.dp),
                value = body,
                onValueChange = { body = it },
                label = { Text("Body") },
                placeholder = { Text("Enter Body") },
                leadingIcon = {
                    Icon(Icons.Default.Book, contentDescription = null)
                },
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                placeholder = { Text("Enter Price") },
                leadingIcon = {
                    Icon(Icons.Default.PriceChange, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = qty,
                onValueChange = { qty = it },
                label = { Text("Qty") },
                placeholder = { Text("Enter Qty") },
                leadingIcon = {
                    Icon(Icons.Default.Numbers, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                placeholder = { Text("Enter category") },
                leadingIcon = {
                    Icon(Icons.Default.Category, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(100.dp),
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image Url") },
                placeholder = { Text("Enter image Url") },
                leadingIcon = {
                    Icon(Icons.Default.Image, contentDescription = null)
                },
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                onClick = {
                    val product = ProductModel(
                        pid = 0,
                        title = title,
                        body = body,
                        price = price,
                        qty = qty,
                        image = imageUrl,
                        category = category,
                    )

                    vm.insertProduct(product)
                },
            ) {
                Text("INSERT PRODUCT")
            }

        if (vm.isLoading) {
            CircularProgressIndicator()
        }

        if (vm.successMessage.isNotEmpty()) {
            Text(vm.successMessage, color = Color.Blue)
        }

        if (vm.errorMessage.isNotEmpty()) {
            Text(vm.errorMessage, color = Color.Red)
        }
        }
    }

}

@Composable
fun SampleLoginUI(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var hidePassword by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Enter Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("Enter Password") },
                leadingIcon = {
                    Icon(Icons.Default.Key, contentDescription = null)
                },
                visualTransformation = if (hidePassword) PasswordVisualTransformation()
                                            else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        hidePassword = !hidePassword
                    }) {
                        if (hidePassword) {
                            Icon(Icons.Default.Visibility, contentDescription = null)
                        } else {
                            Icon(Icons.Default.VisibilityOff, contentDescription = null)
                        }

                    }
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                onClick = {
                    if (email == "kosal" && password == "123") {
                        Toast.makeText(
                            context,
                            "Login Success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Login Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
            ) {
                Text("Login")
            }
        }
    }
}