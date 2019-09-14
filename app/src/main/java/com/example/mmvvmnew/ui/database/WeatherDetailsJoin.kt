package com.example.mmvvmnew.ui.database

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.mmvvmnew.ui.models.SingleWeather
import com.example.mmvvmnew.ui.models.Weather

@Entity(
    tableName = "weather_details_join",
    primaryKeys = arrayOf("weatherID", "detailsID"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Weather::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("weatherID")
        ),
        ForeignKey(
            entity = SingleWeather::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("detailsID")
        )
    )
)
data class WeatherDetailsJoin(
    val weatherID: Int,
    val detailsID: Int
)