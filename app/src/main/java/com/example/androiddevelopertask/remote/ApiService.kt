package com.example.androiddevelopertask.remote

import com.example.androiddevelopertask.model.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getProducts(): ProductResponse
}