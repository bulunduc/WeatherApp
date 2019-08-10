package com.bulunduc.weatherapp.rest

import com.google.gson.annotations.SerializedName

data class Rain(

	@field:SerializedName("3h")
	val jsonMember3h: Double? = null
)