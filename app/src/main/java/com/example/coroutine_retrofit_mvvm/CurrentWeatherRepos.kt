package com.example.coroutine_retrofit_mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class CurrentWeatherRepos @Inject constructor(
    val currentWeatherAPI: CurrentWeatherAPI
) {
    suspend fun getCurrentWeather(
        location: String, apiKey: String
    ): MyResponse<CurrentWeather> {
        return MyResponse.createMyReponse {
            currentWeatherAPI?.getCurrentWeather(location, apiKey)?.execute()
        }
    }
}
