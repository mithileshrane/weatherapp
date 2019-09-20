package com.example.mmvvmnew.ui.main

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mmvvmnew.ui.database.WeatherRepository
import com.example.mmvvmnew.ui.database.WeatherRoomDatabase
import com.example.mmvvmnew.ui.models.SingleWeather
import com.example.mmvvmnew.ui.models.Weather
import kotlinx.coroutines.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import com.example.mmvvmnew.ui.models.Coroutines


class MainViewModel(application: Application) : AndroidViewModel(application) {
    var pusta: List<SingleWeather> = emptyList()

//    val allWords = MutableLiveData<MutableList<SingleWeather>>(pusta.toMutableList())

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WeatherRepository
    // LiveData gives us updated words when they change.

    lateinit var allWords: LiveData<List<SingleWeather>>
    lateinit var allWordsDeferred: Deferred<LiveData<List<SingleWeather>>>
    var progress = MutableLiveData<Int>()

    init {


        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WeatherRepository.
        val weatherDao = WeatherRoomDatabase.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)

        progress.value = View.VISIBLE

        allWordsDeferred = lazy {

            GlobalScope.async(start = CoroutineStart.LAZY) {

                repository.allSingleWeather
            }
        }.value
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(word: Weather) =
        Handler().postDelayed({ viewModelScope.launch { repository.insert(word) } }, 3000)


}
