package ui.mainPage

import core.ApiManager

class MainPageController {
    fun getWeather(): Any {
        return ApiManager.weatherDataApi?.weathers?.get(0)?:"Error"
    }
}