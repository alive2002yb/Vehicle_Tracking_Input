package com.aliveyb.capstoneproject.models

data class predictionResponse(
    val message: String,
    val predict: Double,
    val result: String
)