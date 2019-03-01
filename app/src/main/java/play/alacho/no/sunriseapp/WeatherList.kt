package play.alacho.no.sunriseapp

object WeatherList {
  private val weatherList = arrayListOf<Weather>()

  fun getWeatherList(): List<Weather> {
    return weatherList
  }

  fun addToList(weather: Weather){
    weatherList.add(weather)
  }

  fun removeFromList(weather: Weather){
    weatherList.forEach {
      if(weather.city == it.city){
        weatherList.remove(it)
      }
    }
  }
}