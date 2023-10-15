package ui.loading

import core.ApiManager
import core.ApiWeatherData

class LoadingPanelController {
    fun getWeatherType():String{
        return ApiManager.weatherDataApi?.weathers?.get(0)?.description?:"Error"
    }
}