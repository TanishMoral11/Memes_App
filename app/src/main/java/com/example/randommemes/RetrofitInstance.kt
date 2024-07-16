package com.example.randommemes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //lazy meaning that it is not initialized until it is accessed for the first time
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://meme-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }

}