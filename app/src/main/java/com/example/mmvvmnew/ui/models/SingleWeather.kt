package com.example.mmvvmnew.ui.models

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "weather",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = City::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("cityId")
        ),
        ForeignKey(
            entity = WeatherType::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("weatherTypeId")
        )
    )
)
class SingleWeather {
    @Embedded
    var clouds: Clouds? = null

    @PrimaryKey
    @SerializedName("dt")
    @ColumnInfo(name = "id")
    var id: Int? = null

    var dt_txt: String? = null

    @Embedded
    var main: WeatherProperties? = null

    @Embedded
    var rain: Rain? = null

    @Embedded
    var sys: Sys? = null

    @Ignore
    @Embedded
    var weather: List<WeatherType>? = null

    @Embedded
    var wind: Wind? = null

    var cityId: Int? = null
    var weatherTypeId: Int? = null
}