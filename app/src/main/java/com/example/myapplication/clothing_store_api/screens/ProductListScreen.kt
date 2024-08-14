package com.example.myapplication.clothing_store_api.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.clothing_store_api.models.ProductsItem
import com.example.myapplication.clothing_store_api.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(nav: NavHostController, vm: ProductViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        vm.getProducts()
    }

    var isAscending by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    Button(onClick = {
                        if (isAscending) {
                            vm.sortProductsByPriceHighToLow()
                        } else {
                            vm.sortProductsByPriceLowToHigh()
                        }
                        isAscending = !isAscending
                    }) {
                        Text(if (isAscending) "Sort Price Desc" else "Sort Price Asc")
                    }
                }
            )
        },
    ) {
        if (vm.isLoading) {
            CircularProgressIndicator()
        } else if (vm.errorMessage.isNotEmpty()) {
            Text(text = vm.errorMessage, color = MaterialTheme.colorScheme.error)
        } else {
            ProductList(nav, vm)
        }
    }


}

@Composable
fun ProductList(nav: NavHostController, vm: ProductViewModel) {
    LazyColumn(
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(vm.productsList.size) {
            ProductItem(vm.productsList[it], vm, nav)
        }
    }
}

@Composable
fun ProductItem(productsItem: ProductsItem, vm: ProductViewModel, nav: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {
                vm.setSelectedProductValue(productsItem)
                nav.navigate("detail")
            })

    ) {
        AsyncImage(
            model = productsItem.image,
            contentDescription = productsItem.title,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = productsItem.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Category: ${productsItem.category}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Price: $${productsItem.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Rating: ${productsItem.rating.rate} (${productsItem.rating.count} reviews)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
//            Text(
//                text = productsItem.description,
//                fontSize = 14.sp
//            )
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun ProductListScreenPreview() {
//    val productViewModel: ProductViewModel = viewModel()
//
//    ProductListScreen(productViewModel)
//}