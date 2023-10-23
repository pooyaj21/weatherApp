package core.domain

import core.data.WeatherRepository
import core.model.Weather

class GetCityWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun get(city: String): Result<Weather> {
        return runCatching { weatherRepository.weather(city) }
    }
}