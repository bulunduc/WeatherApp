package com.bulunduc.weatherapp.di

import com.bulunduc.weatherapp.rest.WeatherApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RestModule{
    @Provides
    @Singleton
    fun provideGson() : Gson =
            GsonBuilder()
                .setLenient()
                .create()

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

    @Provides
    @Singleton
    @Named("WEATHER_API")
    fun provideWeatherRetrofit(gson : Gson, okHttpClient: OkHttpClient) : Retrofit =
            Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

    @Provides
    @Singleton
    fun provideWeatherServise(@Named("WEATHER_API") retrofit: Retrofit) : WeatherApi =
            retrofit.create(WeatherApi::class.java)
}