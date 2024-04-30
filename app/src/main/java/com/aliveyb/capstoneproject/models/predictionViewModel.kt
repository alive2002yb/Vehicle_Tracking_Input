package com.aliveyb.capstoneproject.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliveyb.capstoneproject.components.ListtoJson
import com.aliveyb.capstoneproject.components.MaptoJson
import com.aliveyb.capstoneproject.database.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class predictionViewModel : ViewModel() {
    private val baseUrl = "https://capstone-proj-server.onrender.com"
    private val api: Api
    private var prediction = predictionResponse("message",0.0,"result")
    init {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("HTTP_LOG", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(100000, TimeUnit.MILLISECONDS)
            .readTimeout(100000, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
        viewModelScope.launch {
            Log.d("Data","Initialised View Model")
        }
    }
    fun parseData(map: Map<String, Any>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.predictData( MaptoJson(map = map))
                if (response.isSuccessful) {
                    // The API call was successful
                    val predictionResponse = response.body()
                    if (predictionResponse != null) {
                        val message = predictionResponse.message
                        val predict = predictionResponse.predict
                        val result = predictionResponse.result
                        prediction = predictionResponse(message,predict,result)
                        Log.d("predict", "Message: $message, Predict: $predict, Result: $result")
                    } else {
                        // Handle null response body
                        Log.d("predict", "Response body is null")
                    }
                } else {
                    Log.d("predict", "Error body: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("predict", "Error: ${e.message}", e)
            }
        }
    }
    fun getPrediction(): predictionResponse {
        return prediction
    }
}