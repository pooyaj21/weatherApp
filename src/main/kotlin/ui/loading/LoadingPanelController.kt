package ui.loading

import core.ApiManager
import core.ApiWeatherData
import retrofit2.Response

class LoadingPanelController(private val response: ApiWeatherData) {
    fun getDayOrNight(): String {
        return response.weathers[0].icon.last().toString()
    }
    fun getMainName():String{
        return response.weathers[0].main
    }
    fun getIcon():String{
        return response.weathers[0].icon
    }
}