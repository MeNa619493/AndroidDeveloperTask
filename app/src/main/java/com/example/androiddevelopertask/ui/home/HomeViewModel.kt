package com.example.androiddevelopertask.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevelopertask.model.ProductResponse
import com.example.androiddevelopertask.repo.ProductsRepo
import com.example.androiddevelopertask.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val productsRepo: ProductsRepo): ViewModel() {
    private val _products: MutableLiveData<NetworkResult<ProductResponse>> = MutableLiveData()
    val products: LiveData<NetworkResult<ProductResponse>> = _products

    fun getProducts() {
        _products.value = NetworkResult.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.postValue(NetworkResult.Success(productsRepo.getProductsFromRemote()))
            } catch (e: Exception) {
                Log.e("MealsViewModel", e.message.toString())
                _products.postValue(NetworkResult.Error(e.message.toString()))
            }
        }
    }
}