package com.example.mmvvmnew.ui.models

import androidx.room.Entity

//@Entity(tableName = "weather_wind_table")
data class Wind(
    val deg: Double,
    val speed: Double
)