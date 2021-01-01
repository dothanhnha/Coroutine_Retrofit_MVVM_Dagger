package com.example.coroutine_retrofit_mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

data class MyResponse<out T>(val status: MyStatus, val data: T?, val message: String?) {
    companion object {
        val delayResponse:Long = 3000
        fun <T> success(data: T): MyResponse<T> = MyResponse(status = MyStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): MyResponse<T> =
            MyResponse(status = MyStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): MyResponse<T> = MyResponse(status = MyStatus.LOADING, data = data, message = null)

        suspend fun <T> createMyReponse(funcExcuteApi: ()->Response<T>) : MyResponse<out T>{
            var result : MyResponse<out T>
            withContext(Dispatchers.IO){
                try {
                    val response = funcExcuteApi()
                    delay(delayResponse)
                    if (response == null || !response.isSuccessful)
                        result = MyResponse(MyStatus.ERROR, null, response?.message())
                    else
                        result = MyResponse(MyStatus.SUCCESS, response?.body(), response?.message())

                } catch (e: Exception) {
                    result = MyResponse(MyStatus.ERROR, null, e.message)
                }
            }
            return result
        }
    }

}

enum class MyStatus {
    SUCCESS,
    ERROR,
    LOADING
}