package com.eit.myapplication.model


data class ProductResponse(
    val products: List<Product>
)

data class Product(
    val productName: String,
    val productImage: String
)
