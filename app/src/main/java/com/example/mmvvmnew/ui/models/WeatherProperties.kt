package com.example.mmvvmnew.ui.models

import androidx.room.Entity

data class WeatherProperties(
    var grnd_level: Double,
    var humidity: Double,
    var pressure: Double,
    var sea_level: Double,
    var temp: Double,
    var temp_kf: Double,
    var temp_max: Double,
    var temp_min: Double
)