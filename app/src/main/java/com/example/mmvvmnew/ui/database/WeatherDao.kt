package com.example.mmvvmnew.ui.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mmvvmnew.ui.models.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_main_table ORDER BY city ASC")
    fun getAllWords(): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Weather)

    @Query("DELETE FROM weather_main_table")
    suspend fun deleteAll()
}