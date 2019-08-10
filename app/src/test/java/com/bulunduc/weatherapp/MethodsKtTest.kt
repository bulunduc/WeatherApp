package com.bulunduc.weatherapp

import org.junit.Test

import org.junit.Assert.*

class MethodsKtTest {

    @Test
    fun isDayTime() {
        val dateDayTime = "2019.05.08 14:55"
        val dateNightTime = "2019.05.08 20:55"
        val dateOtherFormat = "20:55 2019.12.08"
        val invalidDate = null
        assertTrue(isDayTime(dateDayTime))
        assertFalse(isDayTime(dateNightTime))
        assertFalse(isDayTime(dateOtherFormat))
        assertFalse(isDayTime(invalidDate.toString()))
    }

    @Test
    fun getImageId_dayTime() {
        val isDay = true
        val thunderstormIndex = 299
        val snowIndex = 601
        val rainIndex = 303
        val partCloudIndex = 802
        val allCloudIndex = 805
        val sunIndex = 100
        val nonConditionIndex = 1000
        assertEquals(getImageId(thunderstormIndex, isDay), R.mipmap.thunderstorm)
        assertEquals(getImageId(snowIndex, isDay), R.mipmap.snow)
        assertEquals(getImageId(rainIndex, isDay), R.mipmap.rain)
        assertEquals(getImageId(partCloudIndex, isDay), R.mipmap.part_cloud_rain_sun)
        assertEquals(getImageId(allCloudIndex, isDay), R.mipmap.all_cloud)
        assertEquals(getImageId(sunIndex, isDay), R.mipmap.clear_sun)
        assertEquals(getImageId(nonConditionIndex, isDay), R.mipmap.clear_sun)
    }

    @Test
    fun getImageId_nightTime() {
        val isDay = false
        val thunderstormIndex = 299
        val snowIndex = 601
        val rainIndex = 303
        val partCloudIndex = 802
        val allCloudIndex = 805
        val sunIndex = 100
        val nonConditionIndex = 1000
        assertEquals(getImageId(thunderstormIndex, isDay), R.mipmap.thunderstorm)
        assertEquals(getImageId(snowIndex, isDay), R.mipmap.snow)
        assertEquals(getImageId(rainIndex, isDay), R.mipmap.rain)
        assertEquals(getImageId(partCloudIndex, isDay), R.mipmap.part_cloud_rain_moon)
        assertEquals(getImageId(allCloudIndex, isDay), R.mipmap.all_cloud)
        assertEquals(getImageId(sunIndex, isDay), R.mipmap.clear_moon)
        assertEquals(getImageId(nonConditionIndex, isDay), R.mipmap.clear_moon)
    }

    @Test
    fun getTempStringWithGrade() {
        val positiveTemp = 25
        val negativeTemp = -25
        assertEquals(getTempStringWithGrade(positiveTemp), "25℃")
        assertEquals(getTempStringWithGrade(negativeTemp), "-25℃")
    }
}