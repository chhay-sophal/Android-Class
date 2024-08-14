package com.example.myapplication.product_module.models

import com.google.gson.annotations.SerializedName

data class Product (

    @SerializedName("pid") var pid : String,
    @SerializedName("title") var title : String,
    @SerializedName("body") var body : String,
    @SerializedName("qty") var qty : String,
    @SerializedName("price") var price : String,
    @SerializedName("image") var image : String,
    @SerializedName("category") var category : String

)