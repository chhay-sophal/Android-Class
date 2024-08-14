package com.example.myapplication.clothing_store_api.models

data class ProductsItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

//data class ProductsItem(
//    val category: String = "No",
//    val description: String = "No",
//    val id: Int = 0,
//    val image: String = "No",
//    val price: Double = 0.0,
//    val rating: Rating = Rating(0, 0.0),
//    val title: String = "No"
//)
