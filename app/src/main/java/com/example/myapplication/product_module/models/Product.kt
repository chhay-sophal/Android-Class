package com.example.myapplication.product_module.models

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("pid") var pid: String? = null,
    @SerializedName("title") var title: String,
    @SerializedName("body") var body: String,
    @SerializedName("qty") var qty: String,
    @SerializedName("price") var price: String,
    @SerializedName("image") var image: String,
    @SerializedName("category") var category: String

)

data class ProductResponse (
    @SerializedName("products") val products: List<Product>
)

data class CreateProductResponse (
    @SerializedName("success") val success: Boolean?,
    @SerializedName("product_id") val productId: Int?,
    @SerializedName("error") val error: String?,
    @SerializedName("details") val details: String?
)