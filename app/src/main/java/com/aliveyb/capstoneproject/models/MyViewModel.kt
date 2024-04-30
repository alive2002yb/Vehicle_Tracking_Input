package com.aliveyb.capstoneproject.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliveyb.capstoneproject.components.ListtoJson
import com.aliveyb.capstoneproject.database.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class MyViewModel : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isready = _isReady.asStateFlow()
    private val baseUrl = "https://script.google.com/macros/s/"
    private val api: Api
    init {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("HTTP_LOG", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(0, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
        viewModelScope.launch {
            Log.d("Data","Initialised View Model")
            delay(2000L)
            _isReady.value=true
        }

    }
    fun fetchData(onDataFetched: (List<VehicleProperty>?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = api.getData()
                Log.d("Data",data.toString())
                onDataFetched(data)
            } catch (e: Exception) {
                onDataFetched(null)
            }
        }
    }
    fun getAccuracy(onDataFetched: (List<VehicleProperty>?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = api.getAccuracy()
                onDataFetched(data)
            } catch (e: Exception) {
                onDataFetched(null)
            }
        }
    }
    fun parseData(list: List<String>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.sendData( ListtoJson(list = list))
                if (response.isSuccessful) {
                    Log.d("data", "Data sent successfully")
                } else {
                    Log.d("data", "Error body: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("data", "Error: ${e.message}", e)
            }
        }
    }

}