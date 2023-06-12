package com.example.mypickeoapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "http://192.168.100.17/phpfile/"
    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}