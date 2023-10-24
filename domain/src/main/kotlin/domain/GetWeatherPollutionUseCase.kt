package domain

import data.PollutionRepository
import model.Pollution
import model.Weather

class GetWeatherPollutionUseCase {
    suspend fun get(response: Weather): Result<Pollution> {
        val repository = PollutionRepository()
        return runCatching { repository.pollution(response) }
    }
}