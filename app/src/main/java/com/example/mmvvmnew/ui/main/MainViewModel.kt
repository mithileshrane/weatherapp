package com.example.mmvvmnew.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mmvvmnew.ui.database.WeatherRepository
import com.example.mmvvmnew.ui.database.WeatherRoomDatabase
import com.example.mmvvmnew.ui.models.Weather
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WeatherRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Weather>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WeatherRepository.
        val weatherDao = WeatherRoomDatabase.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
        allWords = repository.allWords
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(word: Weather) = viewModelScope.launch {
        repository.insert(word)
    }

}
