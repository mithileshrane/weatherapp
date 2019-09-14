package com.example.mmvvmnew.ui.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_city_main_table")
data class City(
    @Embedded
    val coord: Coord,
    val country: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)