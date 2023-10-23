package ui.panel.mainPage

import core.service.response.WeatherResponse
import ui.util.convertUTCToLocalHour
import ui.util.getDayOfWeekFromUTC

class MainPageController(private val response: WeatherResponse) {

    fun getDayOrNight(): String {
        return response.weathers[0].icon.last().toString()
    }
    fun getCountry(): String {
        return response.name
    }
    fun getDay(): String {
        return getDayOfWeekFromUTC(response.dt, response.timeZone).toString()
    }
    fun getTime(): String {
        return convertUTCToLocalHour(response.dt, response.timeZone)
    }
    fun getFellingTemp(): Int {
        return ((response.main.feelsLike)-273.15).toInt()
    }
    fun getIcon():String{
        return response.weathers[0].icon
    }

    fun getDescription():String{
        return response.weathers[0].description
    }

    fun getSunRise():String{
        return convertUTCToLocalHour(response.sys.sunrise, response.timeZone)
    }

    fun getSunSet():String{
        return convertUTCToLocalHour(response.sys.sunset, response.timeZone)
    }

    fun getTemp(): Int {
        return ((response.main.temp)-273.15).toInt()
    }

    fun getWind(): Double {
        return response.wind.speed
    }
}