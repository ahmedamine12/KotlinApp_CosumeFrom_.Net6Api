// Product.kt
package com.example.consumedatafrom_netapp.data

data class Product(
    val id: Int,
    val name: String?,
    val description: String?,
    val color: String?,
    val price: Int,
    val image: String?,
    val categoryId: Int,
    val category: Category
)



