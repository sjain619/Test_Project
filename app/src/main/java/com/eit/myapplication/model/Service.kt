package com.eit.myapplication.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface Service {
    //https://mobile-tha-server.firebaseapp.com
    @GET
    suspend fun getProducts(): Response<ProductResponse>

    companion object{
        fun initRetrofit() =
            Retrofit.Builder()
                .baseUrl("https://mobile-tha-server.firebaseapp.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(Service::class.java)
    }
}