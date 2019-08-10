package com.bulunduc.weatherapp.rest

import com.google.gson.annotations.SerializedName
import java.math.RoundingMode

data class Main(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Double? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
){
    val KELVIN : Double = 273.15

    val _temp get() = (temp!! - KELVIN).toInt()
	val _tempMin get() = (tempMin!! - KELVIN).toInt()
	val _tempMax get() = (tempMax!! - KELVIN).toInt()
}