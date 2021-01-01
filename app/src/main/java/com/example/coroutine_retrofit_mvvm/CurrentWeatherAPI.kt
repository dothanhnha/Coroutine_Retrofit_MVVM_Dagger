package com.example.coroutine_retrofit_mvvm

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface CurrentWeatherAPI {
    companion object{
        const val GET_CURRENT_WEATHER = "weather"
    }
    @GET(GET_CURRENT_WEATHER)
    fun getCurrentWeather(@Query("q") location: String,@Query("appid") apiKey: String): Call<CurrentWeather>
}