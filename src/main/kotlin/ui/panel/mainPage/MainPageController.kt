package ui.panel.mainPage

import model.Weather
import ui.util.convertUTCToLocalHour
import ui.util.getDayOfWeekFromUTC

class MainPageController(private val model: Weather) {

    fun getDayOrNight(): String {
        return model.icon.last().toString()
    }
    fun getCountry(): String {
        return model.name
    }
    fun getDay(): String {
        return getDayOfWeekFromUTC(model.date, model.timeZone).toString()
    }
    fun getTime(): String {
        return convertUTCToLocalHour(model.date, model.timeZone)
    }
    fun getFellingTemp(): Int {
        return ((model.feelsLike)-273.15).toInt()
    }
    fun getIcon():String{
        return model.icon
    }

    fun getDescription():String{
        return model.description
    }

    fun getSunRise():String{
        return convertUTCToLocalHour(model.sunRise, model.timeZone)
    }

    fun getSunSet():String{
        return convertUTCToLocalHour(model.sunSet, model.timeZone)
    }

    fun getTemp(): Int {
        return ((model.temp)-273.15).toInt()
    }

    fun getWind(): Double {
        return model.speed
    }
}