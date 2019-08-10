package com.bulunduc.weatherapp.di

import com.bulunduc.weatherapp.activities.MainActivity
import com.bulunduc.weatherapp.fragments.WeatherFragment
import com.bulunduc.weatherapp.mvp.presenter.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter : WeatherPresenter)
    fun inject(fragment : WeatherFragment)
}