package com.eit.myapplication.model

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPresentationData(): Flow<UIState>
}

sealed class UIState{
    data class Response(val data: ProductResponse): UIState()
    data class Error(val errorMessage: String): UIState()
}