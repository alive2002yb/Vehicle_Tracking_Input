package com.aliveyb.capstoneproject.components

import android.util.Log
import com.aliveyb.capstoneproject.models.VehicleItem
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun ListtoJson(list: List<String>): RequestBody
{
    val gson = Gson()
    val json = gson.toJson(list)
    val mediaType = "application/json".toMediaTypeOrNull()
    return json.toRequestBody(mediaType)
}
fun MaptoJson(map: Map<String, Any>): RequestBody {
    val gson = Gson()
    val json = gson.toJson(map)
    Log.d("predict", json)
    val mediaType = "application/json".toMediaTypeOrNull()
    return json.toRequestBody(mediaType)
}