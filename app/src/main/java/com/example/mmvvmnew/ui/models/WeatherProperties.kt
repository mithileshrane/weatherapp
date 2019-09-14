package com.example.mmvvmnew.ui.models

import androidx.room.Entity

data class WeatherProperties(
    val grnd_level: Double,
    val humidity: Double,
    val pressure: Double,
    val sea_level: Double,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)