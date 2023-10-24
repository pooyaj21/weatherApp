package domain

import data.LocationRepository
import model.Weather

class GetWeatherBaseOnIpUseCase(
    private val getIp: GetIpUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) {
    suspend fun get(): Result<Weather> {
        val repository = LocationRepository()
        val ip = getIp.get()
        val city = repository.location(ip).city
        return getCityWeatherUseCase.get(city)
    }
}