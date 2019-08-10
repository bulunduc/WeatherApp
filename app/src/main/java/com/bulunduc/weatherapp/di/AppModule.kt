package com.bulunduc.weatherapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.bulunduc.weatherapp.APP_PREFERENCE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app : App) {
    @Provides
    @Singleton
    fun provideContext() : Context = app

    @Provides
    @Singleton
    fun providePreferences() : SharedPreferences = app.getSharedPreferences(APP_PREFERENCE, MODE_PRIVATE)
}