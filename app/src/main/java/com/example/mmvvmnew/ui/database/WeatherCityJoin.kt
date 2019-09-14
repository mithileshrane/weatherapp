package com.example.mmvvmnew.ui.database

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.mmvvmnew.ui.models.City
import com.example.mmvvmnew.ui.models.Weather


@Entity(
    tableName = "weather_details_join",
    primaryKeys = arrayOf("weatherID", "cityID"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Weather::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("weatherID")
        ),
        ForeignKey(
            entity = City::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("cityID")
        )
    )
)
data class WeatherCityJoin(
    val weatherID: Int,
    val cityID: Int
)