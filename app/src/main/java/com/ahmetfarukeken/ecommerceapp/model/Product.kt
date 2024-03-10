package com.ahmetfarukeken.ecommerceapp.model

import java.io.Serializable

data class Product(
    val name: String,
    val price: Double,
    val currency: String,
    val category: String,
    val image_name: String,
    val color: String,
    var isLiked: Boolean,
    var isAddedToBag: Boolean
): Serializable