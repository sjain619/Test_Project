package com.eit.myapplication.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val service: Service): Repository {

    override fun getPresentationData(): Flow<UIState> {
        return flow {
            val response = service.getProducts()

            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.Response(it))
                }
            }else{
                emit(UIState.Error(response.message()))
            }
        }
    }
}