package com.example.mmvvmnew.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mmvvmnew.R
import com.example.mmvvmnew.ui.models.SingleWeather
import com.example.mmvvmnew.ui.models.Weather
import java.text.DecimalFormat

class WeatherListAdapter internal constructor(
    val context: Context
) : RecyclerView.Adapter<WeatherListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<SingleWeather>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_main_weather: TextView = itemView.findViewById(R.id.tv_main_weather)
        val iv_weather_icon: ImageView = itemView.findViewById(R.id.iv_weather_icon)
        val tv_sub_weather: TextView = itemView.findViewById(R.id.tv_sub_weather)
        val tv_ground_level: TextView = itemView.findViewById(R.id.tv_ground_level)
        val tv_humidity: TextView = itemView.findViewById(R.id.tv_humidity)
        val tv_pressure: TextView = itemView.findViewById(R.id.tv_pressure)
        val tv_sea_level: TextView = itemView.findViewById(R.id.tv_sea_level)
        val tv_temperature: TextView = itemView.findViewById(R.id.tv_temperature)
        val tv_temperature_max: TextView = itemView.findViewById(R.id.tv_temperature_max)
        val tv_temperature_min: TextView = itemView.findViewById(R.id.tv_temperature_min)
        val tv_wind: TextView = itemView.findViewById(R.id.tv_wind)
        val tv_wind_deg: TextView = itemView.findViewById(R.id.tv_wind_deg)
        val tv_wind_speed: TextView = itemView.findViewById(R.id.tv_wind_speed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_weather, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentWeather = words[position]


        holder.tv_main_weather.text= currentWeather.weather?.get(0)?.main
        holder.tv_sub_weather.text= currentWeather.weather?.get(0)?.description
        holder.tv_ground_level.text= context.getString(R.string.txt_ground_level,currentWeather.main?.grnd_level.toString())
        holder.tv_humidity.text= context.getString(R.string.txt_humidity,(currentWeather.main?.humidity).toString())
        holder.tv_pressure.text= context.getString(R.string.txt_pressure,(currentWeather.main?.pressure).toString())
        holder.tv_sea_level.text= context.getString(R.string.txt_sea_level,(currentWeather.main?.sea_level).toString())

        Glide.with(context)
            .load("http://openweathermap.org/img/wn/${currentWeather.weather?.get(0)?.icon}@2x.png")
            .into(holder.iv_weather_icon)

       /* holder.tv_temperature.text= context.getString(R.string.txt_temp,
            DecimalFormat("0.00").format(currentWeather.main.temp-273.15).toString())*/
        holder.tv_temperature.text= context.getString(R.string.txt_temp,"")

        holder.tv_temperature_max.text= context.getString(R.string.txt_temp_max,
            DecimalFormat("0.00").format(currentWeather.main?.temp_max!!-273.15).toString())

        holder.tv_temperature_min.text= context.getString(R.string.txt_temp_min,
            DecimalFormat("0.00").format(currentWeather.main?.temp_min!!-273.15).toString())
    }

    internal fun setWords(words: List<SingleWeather>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}