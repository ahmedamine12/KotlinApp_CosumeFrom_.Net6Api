// MainActivity.kt
package com.example.consumedatafrom_netapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.consumedatafrom_netapp.Services.ProductService
import com.example.consumedatafrom_netapp.data.Product

import com.example.consumedatafrom_netapp.ui.theme.ConsumeDataFrom_netAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:5139/") // Replace with your actual .NET API URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService: ProductService = retrofit.create(ProductService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsumeDataFrom_netAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductListScreen(productService = productService)
                }
            }
        }
    }
}

@Composable
fun ProductListScreen(productService: ProductService) {
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }

    LaunchedEffect(key1 = Unit) {
        products = productService.getProducts()
    }

    Column {
        Text("Product List:")
        Spacer(modifier = Modifier.height(8.dp))
        products.forEach { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column {
        Text("Name: ${product.name}")
        Text("Description: ${product.description}")
        Text("Price: ${product.price}")
        Spacer(modifier = Modifier.height(16.dp))
    }
}
