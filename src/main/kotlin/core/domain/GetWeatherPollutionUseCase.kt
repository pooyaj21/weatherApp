package core.domain

import core.Manager.ApiManager
import core.data.PollutionRepository
import core.model.Pollution
import core.model.Weather
import core.service.response.PollutionResponse
import core.service.response.WeatherResponse

class GetWeatherPollutionUseCase(private val pollutionRepository: PollutionRepository) {
    suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { pollutionRepository.pollution(response) }
    }
}