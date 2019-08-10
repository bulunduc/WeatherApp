package com.bulunduc.weatherapp.di

import com.bulunduc.weatherapp.mvp.presenter.WeatherPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule{

    @Provides
    @Singleton
    fun provideWeatherPresenter() : WeatherPresenter = WeatherPresenter()

}