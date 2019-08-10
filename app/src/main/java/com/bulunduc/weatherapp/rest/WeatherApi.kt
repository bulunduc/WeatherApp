package com.bulunduc.weatherapp.rest

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("x-api-key: a5e21c1ae2a4a720c951faab7f681fe2")
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") city : String = "london"
    ) : Observable<Weather>

    @Headers("x-api-key: a5e21c1ae2a4a720c951faab7f681fe2")
    @GET("forecast")
    fun getForecastWeather(
        @Query("q") city : String = "london"
    ) : Observable<Forecast>
}
