package com.example.myapplication.clothing_store_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.clothing_store_api.screens.ProductDetailsScreen
import com.example.myapplication.clothing_store_api.screens.ProductListScreen
import com.example.myapplication.clothing_store_api.viewmodel.ProductViewModel

@Preview(showSystemUi = true)
@Composable
fun ProductAppMain(){
    val navController = rememberNavController()

    val productVM: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            ProductListScreen(nav = navController, vm = productVM)
        }

        composable(route = "detail") {
            ProductDetailsScreen(nav = navController, vm = productVM)
        }

//        composable(route = "cart") {
//            StateCartScreen(navController = navController, movieVM = movieVM)
//        }
    }
}