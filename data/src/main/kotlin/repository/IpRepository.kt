package repository

import service.service.IpService

interface IpRepository {
    suspend fun ip(): String
}

internal class IpRepositoryImpl(
    private val ipService: IpService
) : IpRepository {
    override suspend fun ip(): String {
        val ipRepository = ipService.getData()
        return ipRepository.ip
    }
}