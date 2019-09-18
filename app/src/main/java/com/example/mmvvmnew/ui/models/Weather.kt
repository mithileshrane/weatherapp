package com.example.mmvvmnew.ui.models

import androidx.annotation.Nullable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "weather_main_table"
)
open class Weather{
    @Ignore
    var city: City?=null

    @PrimaryKey
    @SerializedName("cnt")
    @ColumnInfo(name = "id")
    var id: Int?=0

    var cod: String?=null

    @Ignore
    var list: List<SingleWeather>?=null

    var message: Double?=null

}