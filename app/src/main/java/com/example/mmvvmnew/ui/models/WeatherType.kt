package com.example.mmvvmnew.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "weather_type_table")
data class WeatherType(
    var description: String,
    var icon: String,
    @PrimaryKey
    var id: Int,
    var main: String
)