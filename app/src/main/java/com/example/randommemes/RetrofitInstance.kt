package com.example.randommemes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Lazy initialization of Retrofit instance. It's created when accessed for the first time
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://meme-api.com/")  // Base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON data to Kotlin objects using Gson
            .build()
    }

    // Lazy initialization of the ApiInterface
    val apiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}
