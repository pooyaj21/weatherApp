package domain

import data.WeatherRepository
import model.Weather


class GetCityWeatherUseCase {
    suspend fun get(city: String): Result<Weather> {
        val repository = WeatherRepository()
        return runCatching { repository.weather(city) }
    }
}