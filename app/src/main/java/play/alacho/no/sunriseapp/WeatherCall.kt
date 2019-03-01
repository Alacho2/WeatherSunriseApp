package play.alacho.no.sunriseapp

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import play.alacho.no.sunriseapp.R.id.displayInfo
import java.io.InputStream
import java.lang.ref.WeakReference
import java.math.RoundingMode
import java.net.HttpURLConnection
import java.net.URL

class WeatherCall(activity: MainActivity):AsyncTask<String, String, String>() {

  private val weatherList: WeatherList = WeatherList
  private var minTemp: String = ""
  private var maxTemp: String = ""
  private var mainActivity: WeakReference<Activity> = WeakReference(activity)

  override fun onPreExecute() {

  }

  override fun onProgressUpdate(vararg values: String?) {
  }

  override fun doInBackground(vararg params: String?): String {

    try {
      var url= URL(params[0])
      val connection = url.openConnection() as HttpURLConnection
      connection.connectTimeout = 2500
      //publishProgress(convertStreamToString(connection.inputStream))
      //Send the info to the UI thread when finished
      return convertStreamToString(connection.inputStream)
    } catch(ex:Exception) {
      ex.printStackTrace()
      Log.d(javaClass.simpleName, "Failed when connecting")
    }
    return ""
    //Return empty string to
  }

  override fun onPostExecute(result: String?) {
    val activity = mainActivity.get()!!
    //val json = JSONObject(result)
    val weather: Weather = getWeatherInfo(JSONObject(result))
    val displayInfo: TextView = activity.findViewById(displayInfo)
    //Shorten the result of K -> C to 1 decimal
    displayInfo.text = minTemp
    weatherList.addToList(weather)
  }

  private fun getWeatherInfo(json: JSONObject): Weather{
    val weather = json.getJSONObject("main")
    minTemp = shortenDecimals(weather.getString("temp_min"), 1)
    maxTemp = shortenDecimals(weather.getString("temp_max"), 1)
    val sys = json.getJSONObject("sys") //Reference to country, sun -rise and -fall
    val countryCode = sys.getString("country")
    val city = json.getString("name")
    return Weather(minTemp.toDouble(), maxTemp.toDouble(), city, countryCode)
  }

  private fun convertStreamToString(inputStream: InputStream): String {
    return inputStream.bufferedReader().use { it.readText() }
  }

  private fun shortenDecimals(toShorten: String, decimals: Int): String {
    var convert = (toShorten.toDouble() - 273.15) // - 273.15 is Kelvin
    convert = convert.toBigDecimal().setScale(decimals, RoundingMode.UP).toDouble()
    return convert.toString()
  }
}