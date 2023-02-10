package com.example.androiddevelopertask.di

import com.example.androiddevelopertask.remote.ApiService
import com.example.androiddevelopertask.repo.ProductsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService): ProductsRepo {
        return ProductsRepo(apiService)
    }
}