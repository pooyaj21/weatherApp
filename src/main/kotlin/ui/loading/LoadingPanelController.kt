package ui.loading

import core.ApiManager
import core.ApiWeatherData

class LoadingPanelController {
    fun getDayOrNight(): String {
        return ApiManager.weatherDataApi.weathers[0].icon.last().toString()
    }
    fun getWeatherApi(): ApiWeatherData {
        return ApiManager.weatherDataApi
    }
}