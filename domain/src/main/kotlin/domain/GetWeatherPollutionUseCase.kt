package domain

import data.PollutionRepository
import model.Pollution
import model.Weather

class GetWeatherPollutionUseCase {
    private val repository = PollutionRepository()

    suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { repository.pollution(response) }
    }
}