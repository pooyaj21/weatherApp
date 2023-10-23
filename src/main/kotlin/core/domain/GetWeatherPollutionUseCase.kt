package core.domain

import core.data.PollutionRepository
import core.model.Pollution
import core.model.Weather
class GetWeatherPollutionUseCase(private val pollutionRepository: PollutionRepository) {
    suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { pollutionRepository.pollution(response) }
    }
}