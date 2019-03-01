package play.alacho.no.sunriseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  /**
   * SO WHAT DO WE WANNA DO?
   * We wanna get the result of each search and display it in a recycle view
   * Start by pushing information of each search to an Array of a Weather object
   */

  private val api = "661b8c5b6e790b9cf55403d0af1cebe8"
  lateinit var city: String
  lateinit var countrycode: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun getWeather(view: View) {
    val stringInfo = cityField.text
    val indexOfComma = stringInfo.indexOf(',')

    if(stringInfo.isEmpty() || indexOfComma == -1){
      city = "Oslo"
      countrycode = "No"
    } else {
      city = stringInfo.substring(0, indexOfComma).trim()
      countrycode = stringInfo.substring(indexOfComma+1).trim()
    }

    val weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=$city,$countrycode&APPID=$api"
    WeatherCall(this).execute(weatherURL)
  }

  fun goToRecycler(view: View){
    val intent = Intent(view.context, MainWeather::class.java)
    startActivity(intent)
  }
}
