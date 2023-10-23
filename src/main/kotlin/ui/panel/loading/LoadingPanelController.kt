package ui.panel.loading

import core.service.response.WeatherResponse

class LoadingPanelController(private val response: WeatherResponse) {
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