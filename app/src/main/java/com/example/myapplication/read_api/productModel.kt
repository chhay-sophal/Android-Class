package com.firstapp.jetpack1.myOwnAPI

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("pid") var pid: Int,
    @SerializedName("title") var title: String,
    @SerializedName("body") var body: String,
    @SerializedName("qty") var qty: String,
    @SerializedName("price") var price: String,
    @SerializedName("image") var image: String,
    @SerializedName("category") var category: String
)
