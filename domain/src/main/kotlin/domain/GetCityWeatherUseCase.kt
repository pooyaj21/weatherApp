package domain

import di.RepositoryProvider
import model.Weather
import repository.WeatherRepository

interface GetCityWeatherUseCase {
    suspend fun get(city: String): Result<Weather>
}

internal class GetCityWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : GetCityWeatherUseCase {
    override suspend fun get(city: String): Result<Weather> {
        return runCatching { weatherRepository.weather(city) }
    }
}