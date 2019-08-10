package com.bulunduc.weatherapp

import java.util.*

fun getCityList(): TreeMap<String, String> {
    var list: TreeMap<String, String> = TreeMap()
    list["Moscow"] = "GMT+03"
    list["Boston"] = "GMT-05"
    list["Paris"] = "GMT+01"
    list["London"] = "GMT+00"
    return list
}
fun isDayTime(date: String): Boolean {
    val validDateFormat = Regex(pattern = """^\d{4}\.\d{2}\.\d{2}\s\d{2}:\d{2}$""")
    if (!validDateFormat.matches(input = date)) return false
    val regex = Regex(pattern = """^1\d|^0[7-9]""")
    return regex.containsMatchIn(input = date.subSequence(11 until date.length))
}

fun getImageId(id: Int, isDay: Boolean): Int {
    var resourceId = if (isDay) R.mipmap.clear_sun else R.mipmap.clear_moon
    if (id in 200..299) resourceId = R.mipmap.thunderstorm
    else if (id in 300..599) resourceId = R.mipmap.rain
    else if (id in 600..700) resourceId = R.mipmap.snow
    else if (id == 800 && isDay) resourceId = R.mipmap.clear_sun
    else if (id == 800 && !isDay) resourceId = R.mipmap.clear_moon
    else if (id in 801..802 && isDay) resourceId = R.mipmap.part_cloud_rain_sun
    else if (id in 801..802 && !isDay) resourceId = R.mipmap.part_cloud_rain_moon
    else if (id in 803..809) resourceId = R.mipmap.all_cloud
    return resourceId
}

fun getTempStringWithGrade(temp : Int):String{
    return "${temp}\u2103"
}