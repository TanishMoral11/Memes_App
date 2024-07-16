package com.example.randommemes

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("gimme")
    fun getData() : Call<responseDataclass>
}