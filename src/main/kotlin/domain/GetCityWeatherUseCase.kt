package domain

import core.ApiManager
import core.ApiPollutionData
import core.ApiWeatherData

class GetCityWeatherUseCase(private val apiManager: ApiManager) {
    suspend fun getWeather(city: String): Result<ApiWeatherData> {
        return runCatching { apiManager.weatherApiCreator(city) }
    }
    suspend fun transferApi(api:ApiWeatherData):Result<ApiWeatherData>{
        return runCatching { api }
    }
    suspend fun getPollution(api: ApiWeatherData): Result<ApiPollutionData> {
        return runCatching { apiManager.pollutionApiCreator(api) }
    }
}