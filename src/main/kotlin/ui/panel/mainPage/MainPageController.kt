package ui.panel.mainPage

import core.model.Weather
import ui.util.convertUTCToLocalHour
import ui.util.getDayOfWeekFromUTC

class MainPageController(private val response: Weather) {

    fun getDayOrNight(): String {
        return response.icon.last().toString()
    }
    fun getCountry(): String {
        return response.name
    }
    fun getDay(): String {
        return getDayOfWeekFromUTC(response.date, response.timeZone).toString()
    }
    fun getTime(): String {
        return convertUTCToLocalHour(response.date, response.timeZone)
    }
    fun getFellingTemp(): Int {
        return ((response.feelsLike)-273.15).toInt()
    }
    fun getIcon():String{
        return response.icon
    }

    fun getDescription():String{
        return response.description
    }

    fun getSunRise():String{
        return convertUTCToLocalHour(response.sunRise, response.timeZone)
    }

    fun getSunSet():String{
        return convertUTCToLocalHour(response.sunSet, response.timeZone)
    }

    fun getTemp(): Int {
        return ((response.temp)-273.15).toInt()
    }

    fun getWind(): Double {
        return response.speed
    }
}