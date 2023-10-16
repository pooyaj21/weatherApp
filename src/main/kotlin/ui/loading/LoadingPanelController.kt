package ui.loading

import core.ApiManager
import core.ApiWeatherData

class LoadingPanelController {
    fun getWeatherApi(): ApiWeatherData {
        return ApiManager.weatherDataApi
    }
}