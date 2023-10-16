package ui.airPollution

import core.ApiManager

class AirPollutionController {
    fun getDayOrNight(): String {
        return ApiManager.weatherDataApi.weathers[0].icon.last().toString()
    }
    fun getIcon():String{
        return ApiManager.weatherDataApi.weathers[0].icon
    }
    fun getDescription():String{
        return ApiManager.weatherDataApi.weathers[0].description
    }
    fun getCo():String{
        return ApiManager.pollutionDataApi.list[0].components.co.toString()
    }
    fun getNo2():String{
        return ApiManager.pollutionDataApi.list[0].components.no2.toString()
    }
    fun getNo():String{
        return ApiManager.pollutionDataApi.list[0].components.no.toString()
    }
    fun getO3():String{
        return ApiManager.pollutionDataApi.list[0].components.o3.toString()
    }
}