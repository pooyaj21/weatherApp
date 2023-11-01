package domain

import di.RepositoryProvider
import model.Pollution
import model.Weather

class GetPollutionUseCase {
    private val repository = RepositoryProvider.pollutionRepository()

    suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { repository.pollution(response) }
    }
}