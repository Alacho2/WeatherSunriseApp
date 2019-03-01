package play.alacho.no.sunriseapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.weather_item_row.view.*

class WeatherAdapter:RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WeatherHolder {
    return WeatherHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item_row, parent, false))
  }

  override fun getItemCount(): Int {
    return WeatherList.getWeatherList().count()
  }

  override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
    val weatherItem: Weather = WeatherList.getWeatherList()[position]
    holder.bindWeather(weatherItem)
  }

  class WeatherHolder(var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    var city: String = ""
    var country: String = ""
    var minTemp: Double = 0.0
    var maxTemp: Double = 0.0

    init {
      view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

    fun bindWeather(weather: Weather){
      this.city = weather.city
      this.country = weather.country
      this.minTemp = weather.minTemp
      this.maxTemp = weather.maxTemp
      view.cityText.text = city
      view.countryCodeText.text = country
      view.minTempText.text = minTemp.toString()
      view.maxTempText.text = maxTemp.toString()
    }

  }

}
