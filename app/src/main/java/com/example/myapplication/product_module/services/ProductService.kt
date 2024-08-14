package com.example.myapplication.product_module.services

import retrofit2.http.GET

const val originalURL = "http://localhost:8080/android_class/product/"
const val PRODUCT_BASE_URL = "http://localhost:8080"

interface ProductService {
    @GET()
}