package com.example.mmvvmnew.ui.models

import androidx.room.Entity

//@Entity(tableName = "weather_type_table")
data class WeatherType(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)