package core.domain

import data.PollutionRepository
import model.Pollution
import model.Weather
class GetWeatherPollutionUseCase(private val pollutionRepository: PollutionRepository) {
    suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { pollutionRepository.pollution(response) }
    }
}