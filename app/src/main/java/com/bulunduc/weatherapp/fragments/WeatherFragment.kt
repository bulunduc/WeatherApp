package com.bulunduc.weatherapp.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import com.bulunduc.weatherapp.di.App
import com.bulunduc.weatherapp.mvp.contract.WeatherContract
import com.bulunduc.weatherapp.mvp.presenter.WeatherPresenter
import com.bulunduc.weatherapp.rest.Forecast
import com.bulunduc.weatherapp.rest.ListItem
import com.bulunduc.weatherapp.rest.Weather
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_weather.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import javax.inject.Inject
import android.widget.ArrayAdapter
import com.bulunduc.weatherapp.*
import java.util.*

class WeatherFragment : Fragment(), WeatherContract.View {

    @Inject
    lateinit var presenter: WeatherPresenter

    @Inject
    lateinit var pref: SharedPreferences

    override fun showErrorMessage(error: String?) {
        Log.d("WeatherFragment", error)
    }

    override fun updateWeatherView(weather: Weather) {
        let {
            val sdf = SimpleDateFormat("yyyy-MM-dd\nHH:mm")
            sdf.timeZone = TimeZone.getTimeZone(getCityList()[city.selectedItem])
            val date = sdf.format(Date())
            currentTime.text = date
            currentTemp.text = getTempStringWithGrade(weather.main?._temp!!)
            minMaxTemp.text = getString(
                R.string.min_max_temp,
                getTempStringWithGrade(weather.main._tempMin),
                getTempStringWithGrade(weather.main._tempMax)
            )

            Glide
                .with(this)
                .load(getImageId(weather.weather?.get(0)?.id!!, isDayTime(date)))
                .into(weatherIcon)
            windValue.text = getString(R.string.wind_value, weather.wind?.speed.toString())
            humidityValue.text = getString(R.string.humidity_value, weather.main.humidity.toString())
            pressureValue.text = getString(R.string.pressure_value, weather.main.pressure.toString())
        }
    }

    override fun updateForecastView(forecast: Forecast) {
        var list: List<ListItem?>? = forecast.list
        var temp = StringBuilder()
        var day = 1
        for (i in list!!) {
            if (i?.dtTxt?.endsWith("00:00:00", true)!!) {
                temp.append(getTempStringWithGrade(i.main?._tempMin!!))
            } else if (i.dtTxt.endsWith("12:00:00", true)) {
                if (temp.isNotEmpty()) {
                    temp.append(" / ")
                    temp.append(getTempStringWithGrade(i.main?._tempMax!!))
                    when (day) {
                        1 -> {
                            firstDayDate.text = i.dtTxt.subSequence(0..10)
                            firstDayMinMaxTemp.text = temp
                            Glide
                                .with(this)
                                .load(getImageId(i.weather?.get(0)?.id!!, true))
                                .into(firstDayWeatherIcon)
                        }
                        2 -> {
                            secondDayDate.text = i.dtTxt.subSequence(0..10)
                            secondDayMinMaxTemp.text = temp
                            Glide
                                .with(this)
                                .load(getImageId(i.weather?.get(0)?.id!!, true))
                                .into(secondDayWeatherIcon)
                        }
                        3 -> {
                            thirdDayDate.text = i.dtTxt.subSequence(0..10)
                            thirdDayMinMaxTemp.text = temp
                            Glide
                                .with(this)
                                .load(getImageId(i.weather?.get(0)?.id!!, true))
                                .into(thirdDayWeatherIcon)
                        }
                    }
                    day++
                    temp.clear()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        initFunctionality()
        updateFragment(getCurrentCity(pref))
    }

    private fun initFunctionality(){
        val spinnerAdapter = ArrayAdapter<String>(
            activity,
            android.R.layout.simple_spinner_item, getCityList().keys.toTypedArray()
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        city.adapter = spinnerAdapter
        city.setSelection(spinnerAdapter.getPosition(getCurrentCity(pref)))
        city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                saveCurrentCity(pref, city.adapter.getItem(position).toString())
                updateFragment(city.adapter.getItem(position).toString())
            }
        }

        fab_update_weather.setOnClickListener {
            updateFragment(getCurrentCity(pref))
        }
    }

    private fun updateFragment(city: String) {
        presenter.getWeatherByCity(city)
        presenter.getForecastByCity(city)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }


}