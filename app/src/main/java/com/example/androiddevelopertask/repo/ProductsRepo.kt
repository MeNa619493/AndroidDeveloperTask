package com.example.androiddevelopertask.repo

import com.example.androiddevelopertask.model.ProductResponse
import com.example.androiddevelopertask.remote.ApiService

class ProductsRepo(private val apiService: ApiService) {
    suspend fun getProductsFromRemote(): ProductResponse = apiService.getProducts()
}