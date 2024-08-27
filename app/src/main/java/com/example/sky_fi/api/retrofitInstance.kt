package com.example.sky_fi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com";

    private fun getInstance(): Retrofit{
        return Retrofit.Builder().
        baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).
        build()
    }

    val weatherApi : weatherApi = getInstance().create(weatherApi::class.java)
}