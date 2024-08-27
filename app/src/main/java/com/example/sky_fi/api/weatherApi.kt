package com.example.sky_fi.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey : String,
        @Query("q") city : String
    ): Response<WeatherModel>
}