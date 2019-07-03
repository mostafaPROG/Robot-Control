package com.example.sampleesp.api

import com.example.sampleesp.MyCalback
import retrofit2.Call
import retrofit2.http.*

interface ESPApi {
    @GET("/{led}")
    fun setLED(@Path("led") lat: String): Call<MyCalback>?
}