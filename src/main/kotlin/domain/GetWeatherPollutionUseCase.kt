package domain

import core.ApiManager
import core.ApiPollutionData
import core.ApiWeatherData

class GetWeatherPollutionUseCase(private val apiManager: ApiManager) {
    suspend fun get(response: ApiWeatherData): Result<ApiPollutionData> {
        return runCatching { apiManager.pollutionApiCreator(response) }
    }
}