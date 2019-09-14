package com.example.mmvvmnew.ui.models

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "weather_main_table"
)
data class Weather(
   /* @Nullable
    val city: City,*/
    @PrimaryKey
    @SerializedName("cnt")
    val id: Int,
    val cod: String,
   /* @Nullable
    val list: List<SingleWeather>,*/
    val message: Double
)