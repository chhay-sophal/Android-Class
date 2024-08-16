package com.example.myapplication.product_module.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("pid") var pid: String,
    @SerializedName("title") var title: String,
    @SerializedName("body") var body: String,
    @SerializedName("qty") var qty: String,
    @SerializedName("price") var price: String,
    @SerializedName("image") var image: String,
    @SerializedName("category") var category: String
)

data class CreateProductRequest(
    @SerializedName("title") var title: String,
    @SerializedName("body") var body: String,
    @SerializedName("qty") var qty: String,
    @SerializedName("price") var price: String,
    @SerializedName("image") var image: String,
    @SerializedName("category") var category: String
)

data class UpdateProductRequest(
    @SerializedName("pid") var pid: String,
    @SerializedName("title") var title: String? = null,
    @SerializedName("body") var body: String? = null,
    @SerializedName("qty") var qty: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("category") var category: String? = null
)

data class ProductResponse (
    @SerializedName("products") val products: List<Product>,
    @SerializedName("error") val error: String? = null
)

data class ApiResponse (
    @SerializedName("message") val message: String? = null,
    @SerializedName("error") val error: String? = null,
    @SerializedName("pid") val pid: String? = null
)