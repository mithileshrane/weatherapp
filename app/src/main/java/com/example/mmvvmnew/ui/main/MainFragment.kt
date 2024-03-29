package com.example.mmvvmnew.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmvvmnew.R
import com.example.mmvvmnew.ui.models.Coroutines
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import com.google.gson.Gson
import com.example.mmvvmnew.ui.models.Weather
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mngr = LinearLayoutManager(activity!!)

        recylview?.layoutManager = mngr
//        recylview?.addItemDecoration(DividerItemDecoration(activity!!, mngr.orientation))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = WeatherListAdapter(activity!!)
        recylview.adapter = adapter
        recylview.layoutManager = LinearLayoutManager(activity!!)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

       viewModel.progress.observe(this, Observer {
            when(it){
                View.VISIBLE->{
                    Log.d("SUB", "VISIBLE")
                    pBarLayout?.visibility=View.VISIBLE
                }
                View.GONE->{
                    Log.d("SUB", "GONE")
                    pBarLayout?.visibility=View.GONE
                }
            }
        })



        Coroutines.main {
            viewModel.allWordsDeferred.await().observe(this, Observer { weatherList ->
                // Update the cached copy of the words in the adapter.
                Log.d("SUB", "Observer")
                if (weatherList.isNotEmpty())
                    viewModel.progress.value=View.GONE
                weatherList?.let { adapter.setWords(it) }
            })
        }


        val compositeDisposable = CompositeDisposable()
        val observable = object : Observable<String>() {
            override fun subscribeActual(observer: io.reactivex.rxjava3.core.Observer<in String>?) {
            }
        }


        val observer = object : io.reactivex.rxjava3.core.Observer<String> {

            override fun onComplete() {
                Log.d("SUB", "onComplete")
            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(t: String?) {
                Log.d("SUB", "onNext ${t}")
            }

            override fun onError(e: Throwable?) {

            }
        }

        compositeDisposable.add(observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe { observer })


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        /*viewModel.allWords.observe(this, Observer { weatherList ->
            // Update the cached copy of the words in the adapter.
            weatherList?.let { adapter.setWords(it) }
        })
*/
//        viewModel.allWords.value=weather.list?.toMutableList()

    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val istream = activity!!.assets.open("json.txt")
            val size = istream.available()
            val buffer = ByteArray(size)
            istream.read(buffer)
            istream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    override fun onResume() {
        super.onResume()
        val gson = Gson()
        val weather = gson.fromJson(loadJSONFromAsset(), Weather::class.java)
        viewModel.insert(weather)
    }
}
