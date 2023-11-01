package data

import service.di.ServiceProvider

interface IpRepository {
    suspend fun ip(): String
}
internal class IpRepositoryImpl : IpRepository {
    override suspend fun ip(): String {
        val ipRepository = ServiceProvider.ipService().getData()
        return ipRepository.ip
    }
}