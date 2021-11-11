package com.eit.myapplication.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eit.myapplication.model.Repository
import com.eit.myapplication.model.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: Repository): ViewModel() {

    private val _products = MutableLiveData<UIState>()
    val products: LiveData<UIState>
    get() = _products

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    init {
        coroutineScope.launch {
            val response = repository.getPresentationData()
            response.collect {
                _products.postValue(it)
            }
        }
    }
}