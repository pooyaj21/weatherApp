package core.domain

import data.WeatherRepository
import model.Weather


class GetCityWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun get(city: String): Result<Weather> {
        return runCatching { weatherRepository.weather(city) }
    }
}