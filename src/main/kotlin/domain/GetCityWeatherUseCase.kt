package domain

import core.ApiManager
import core.ApiWeatherData

class GetCityWeatherUseCase(private val apiManager: ApiManager) {
    suspend fun get(city: String): Result<ApiWeatherData> {
        return runCatching { apiManager.weatherApiCreator(city) }
    }
}