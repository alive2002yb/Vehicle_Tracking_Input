package com.aliveyb.capstoneproject.database
import com.aliveyb.capstoneproject.models.VehicleProperty
import com.aliveyb.capstoneproject.models.predictionResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("AKfycbwOXX5lIx66s3vB0evpVsKuJwqFhRk9QfKsSvHZii40Kn7c6fSKnLhVXWrv3pDY4R8/exec?action=get")
    suspend fun getData(): List<VehicleProperty>
    @GET("AKfycbwOXX5lIx66s3vB0evpVsKuJwqFhRk9QfKsSvHZii40Kn7c6fSKnLhVXWrv3pDY4R8/exec?action=getAccuracy")
    suspend fun getAccuracy(): List<VehicleProperty>
    @POST("AKfycbwOXX5lIx66s3vB0evpVsKuJwqFhRk9QfKsSvHZii40Kn7c6fSKnLhVXWrv3pDY4R8/exec?action=parse")
    suspend fun sendData(@Body requestBody: RequestBody): Response<Void>
    @POST("/predictModel")
    suspend fun predictData(@Body requestBody: RequestBody): Response<predictionResponse>
}