package play.alacho.no.sunriseapp

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_main_weather.*

class MainWeather : AppCompatActivity() {

  lateinit var layoutManager: LinearLayoutManager
  lateinit var adapter: WeatherAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_weather)

    layoutManager = LinearLayoutManager(this)
    weatherRecycler.layoutManager = layoutManager
    adapter = WeatherAdapter()
    weatherRecycler.adapter = adapter

    floatingActionButton.setOnClickListener {
      InputDialog()
    }
  }

  private fun showDiag() {
    val view: View = View.inflate(this, R.layout.input_dialog, null)
    val dialog = Dialog(this, R.style.WeatherTheme)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(view)

    //dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    dialog.show()
  }

  fun someCode(view: View){

  }
}
