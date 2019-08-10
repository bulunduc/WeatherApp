package com.bulunduc.weatherapp.mvp.contract

import com.bulunduc.weatherapp.rest.Forecast
import com.bulunduc.weatherapp.rest.Weather

class WeatherContract {
    interface View : BaseContract.View{
        fun showErrorMessage(error: String?)
        fun updateWeatherView(weather: Weather)
        fun updateForecastView(forecast: Forecast)
    }

    abstract class Presenter: BaseContract.Presenter<View>(){
        abstract fun getWeatherByCity(id : String)
        abstract fun getForecastByCity(id : String)
    }
}