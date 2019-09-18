package com.example.mmvvmnew.ui.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mmvvmnew.ui.models.City
import com.example.mmvvmnew.ui.models.SingleWeather
import com.example.mmvvmnew.ui.models.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_main_table ORDER BY id ASC")
    fun getAllWords(): LiveData<List<Weather>>

    @Query("SELECT * from weather ORDER BY id ASC")
    fun getAllSingleWeather(): LiveData<List<SingleWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(singleWeather: List<SingleWeather>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: SingleWeather)

    @Query("DELETE FROM weather_main_table")
    suspend fun deleteAll()
}