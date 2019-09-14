package com.example.mmvvmnew.ui.database

import androidx.lifecycle.LiveData
import com.example.mmvvmnew.ui.models.Weather

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WeatherRepository(private val wordDao: WeatherDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Weather>> = wordDao.getAllWords()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(word: Weather) {
        wordDao.insert(word)
    }
}