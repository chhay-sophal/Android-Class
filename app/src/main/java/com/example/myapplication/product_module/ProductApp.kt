package com.example.myapplication.product_module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.product_module.screens.ProductCreateScreen
import com.example.myapplication.product_module.screens.ProductDeleteScreen
import com.example.myapplication.product_module.screens.ProductDetailScreen
import com.example.myapplication.product_module.screens.ProductScreen
import com.example.myapplication.product_module.screens.ProductUpdateScreen
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@Preview(showSystemUi = true)
@Composable
fun ProductApp(){
    val viewModel = ProductViewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { ProductScreen(viewModel, navController) }

        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let {
                ProductDetailScreen(
                    viewModel = viewModel,
                    productId = it,
                    onNavigateToUpdate = { product ->
                        navController.navigate("update_product/${product.pid}")
                    },
                    onNavigateToDelete = { product ->
                        navController.navigate("delete_product/${product.pid}")
                    },
                    onBackPressed = { navController.popBackStack() }
                )
            }
        }

        composable("create_product") {
            ProductCreateScreen(
                viewModel, onProductCreated = { navController.popBackStack() },
                onBackPressed = {navController.popBackStack()}
            )
        }

        composable("update_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            val product = viewModel.products.find { it.pid == productId } ?: return@composable
            ProductUpdateScreen(viewModel, product, onProductUpdated = { navController.popBackStack() }, onBackPressed = { navController.popBackStack() })
        }

        composable("delete_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            val product = viewModel.products.find { it.pid == productId } ?: return@composable
            ProductDeleteScreen(viewModel, product, onProductDeleted = { navController.popBackStack() })
        }
    }
}