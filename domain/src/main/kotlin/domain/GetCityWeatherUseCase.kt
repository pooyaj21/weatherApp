package domain

import data.WeatherRepository
import model.Weather


class GetCityWeatherUseCase {
    private val repository = WeatherRepository()
    suspend fun get(city: String): Result<Weather> {
        return runCatching { repository.weather(city) }
    }
}