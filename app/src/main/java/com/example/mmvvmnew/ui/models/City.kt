package com.example.mmvvmnew.ui.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_city_main_table")
data class City(
    @Embedded
    var coord: Coord,
    var country: String,
    @PrimaryKey
    var id: Int,
    var name: String,
    var sunrise: Int,
    var sunset: Int,
    var timezone: Int
)