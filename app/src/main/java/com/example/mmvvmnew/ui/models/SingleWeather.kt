package com.example.mmvvmnew.ui.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "weather")
data class SingleWeather(
    @Embedded
    val clouds: Clouds,
    @PrimaryKey
    @SerializedName("dt")
    val id: Int,
    val dt_txt: String,
    @Embedded
    val main: WeatherProperties,
    @Embedded
    val rain: Rain,
    @Embedded
    val sys: Sys,
    @Embedded
    val weather: List<WeatherType>,
    @Embedded
    val wind: Wind
)