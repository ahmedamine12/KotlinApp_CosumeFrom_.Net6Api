package com.example.consumedatafrom_netapp.Services

import com.example.consumedatafrom_netapp.data.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("api/products")
    suspend fun getProducts(): List<Product>

    @GET("api/products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Product
}
