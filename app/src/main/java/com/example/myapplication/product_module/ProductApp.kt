package com.example.myapplication.product_module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.product_module.screens.ProductScreen
import com.example.myapplication.product_module.viewmodels.ProductViewModel

@Preview(showSystemUi = true)
@Composable
fun ProductApp(){
    val vm = ProductViewModel()
    ProductScreen(vm)
}