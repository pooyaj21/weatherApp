package ui.mainPage

import core.ApiManager
import ui.util.convertUTCToLocalHour
import ui.util.getDayOfWeekFromUTC

class MainPageController {

    fun getDayOrNight(): String {
        return ApiManager.weatherDataApi.weathers[0].icon.last().toString()
    }
    fun getCountry(): String {
        return ApiManager.weatherDataApi.name
    }
    fun getDay(): String {
        return getDayOfWeekFromUTC(ApiManager.weatherDataApi.dt, ApiManager.weatherDataApi.timeZone).toString()
    }
    fun getTime(): String {
        return convertUTCToLocalHour(ApiManager.weatherDataApi.dt, ApiManager.weatherDataApi.timeZone)
    }
    fun getFellingTemp(): Int {
        return ((ApiManager.weatherDataApi.main.feelsLike)-273.15).toInt()
    }
    fun getIcon():String{
        return ApiManager.weatherDataApi.weathers[0].icon
    }

    fun getDescription():String{
        return ApiManager.weatherDataApi.weathers[0].description
    }

    fun getSunRise():String{
        return convertUTCToLocalHour(ApiManager.weatherDataApi.sys.sunrise, ApiManager.weatherDataApi.timeZone)
    }

    fun getSunSet():String{
        return convertUTCToLocalHour(ApiManager.weatherDataApi.sys.sunset, ApiManager.weatherDataApi.timeZone)
    }

    fun getTemp(): Int {
        return ((ApiManager.weatherDataApi.main.temp)-273.15).toInt()
    }

    fun getWind(): Double {
        return ApiManager.weatherDataApi.wind.speed
    }
}