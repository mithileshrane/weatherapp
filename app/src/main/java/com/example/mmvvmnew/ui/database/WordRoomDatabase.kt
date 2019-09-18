package com.example.mmvvmnew.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mmvvmnew.ui.models.City
import com.example.mmvvmnew.ui.models.SingleWeather
import com.example.mmvvmnew.ui.models.Weather
import com.example.mmvvmnew.ui.models.WeatherType

@Database(
    entities = arrayOf(
        /* City::class, Clouds::class, Coord::class,
         Rain::class, SingleWeather::class, Sys::class, Weather::class,
         WeatherProperties::class, WeatherType::class, Wind::class*/
        Weather::class, City::class, SingleWeather::class/*, WeatherType::class*//*,WeatherDetailsJoin::class,
        WeatherCityJoin::class*/
    ), version = 1, exportSchema = false
)
public abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(context: Context): WeatherRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                ).setJournalMode(JournalMode.TRUNCATE)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}