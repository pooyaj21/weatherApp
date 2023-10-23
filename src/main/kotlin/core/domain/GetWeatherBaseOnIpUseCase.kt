package core.domain

import core.data.LocationRepository
import core.model.Weather

class GetWeatherBaseOnIpUseCase(
    private val locationRepository: LocationRepository,
    private val getIp: GetIpUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) {
    suspend fun get(): Result<Weather> {
        val ip = getIp.get()
        val city = locationRepository.location(ip).city
        return  getCityWeatherUseCase.get(city)
    }
}