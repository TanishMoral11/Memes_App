package com.example.randommemes

import retrofit2.Call
import retrofit2.http.GET

// Interface to define the API endpoints
interface ApiInterface {

    // A GET request to the endpoint "gimme" which returns a Call object containing a response of type `responseDataclass`
    @GET("gimme")
    fun getData(): Call<responseDataclass>
}
