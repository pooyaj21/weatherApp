package domain

import repository.IpRepository

interface GetIpUseCase{
    suspend fun get(): String
}
internal class GetIpUseCaseImpl(
    private val ipRepository : IpRepository
) :GetIpUseCase{
   override suspend fun get(): String {
        return ipRepository.ip()
    }

}