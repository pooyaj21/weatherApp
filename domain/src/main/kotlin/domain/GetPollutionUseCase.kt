package domain

import di.RepositoryProvider
import model.Pollution
import model.Weather
import repository.IpRepository
import repository.PollutionRepository

interface GetPollutionUseCase {
    suspend fun get(response: Weather): Result<Pollution>

}

internal class GetPollutionUseCaseImpl(
    private val pollutionRepository: PollutionRepository
) : GetPollutionUseCase {

    override suspend fun get(response: Weather): Result<Pollution> {
        return runCatching { pollutionRepository.pollution(response) }
    }
}