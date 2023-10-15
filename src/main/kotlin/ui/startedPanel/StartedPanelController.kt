package ui.startedPanel

import core.ApiManager
import core.ApiWeatherData
import core.WeatherApiService
import ui.RoundedTextField

class StartedPanelController {
     suspend fun getCity(text:String?): ApiWeatherData {
         if (text.isNullOrEmpty()) TODO("add error")
         else {
             return ApiManager.weatherApiCreator(text)
         }
    }
}