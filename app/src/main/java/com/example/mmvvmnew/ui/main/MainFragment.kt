package com.example.mmvvmnew.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmvvmnew.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import com.google.gson.Gson
import com.example.mmvvmnew.ui.models.Weather


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mngr=LinearLayoutManager(activity!!)

        recylview?.layoutManager=mngr
        recylview?.addItemDecoration(DividerItemDecoration(activity!!, mngr.orientation))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val jsonObject=JSONObject()
        val gson = Gson()
        val weather = gson.fromJson(loadJSONFromAsset(), Weather::class.java)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.insert(weather)


    }

    fun loadJSONFromAsset():String?{
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

}
