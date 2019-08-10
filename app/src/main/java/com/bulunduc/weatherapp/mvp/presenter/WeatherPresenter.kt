package com.bulunduc.weatherapp.mvp.presenter

import com.bulunduc.weatherapp.di.App
import com.bulunduc.weatherapp.mvp.contract.WeatherContract
import com.bulunduc.weatherapp.rest.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherPresenter : WeatherContract.Presenter() {
    override fun getWeatherByCity(id: String) {
        subcribe(weatherApi.getCurrentWeather(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> view.updateWeatherView(result) },
                { error -> view.showErrorMessage(error.message) }
            )

        )
    }

    override fun getForecastByCity(id: String) {
        subcribe(
            weatherApi.getForecastWeather(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> view.updateForecastView(result) },
                    { error -> view.showErrorMessage(error.message) }
                )
        )
    }

    @Inject
    lateinit var weatherApi: WeatherApi

    init {
        App.appComponent.inject(this)
    }
}