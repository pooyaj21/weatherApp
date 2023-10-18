package domain

import core.ApiManager
import core.ApiWeatherData

class GetCityBaseOnIp(private val apiManager: ApiManager) {
    suspend fun get(): Result<ApiWeatherData> {
        val ip = GetIp().get()
        return runCatching { apiManager.weatherApiCreatorBaseOnLocation(ip) }
    }
}