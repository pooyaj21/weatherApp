package domain

import di.RepositoryProvider
import model.Weather


class GetCityWeatherUseCase {
    private val repository = RepositoryProvider.weatherRepository()
    suspend fun get(city: String): Result<Weather> {
        return runCatching { repository.weather(city) }
    }
}