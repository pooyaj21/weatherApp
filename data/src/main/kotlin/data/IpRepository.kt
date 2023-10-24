package data

import di.RepositoryProvider

class IpRepository {
    suspend fun ip(): String {
        val ipRepository = RepositoryProvider.providerIpRepository().getData()
        return ipRepository.ip
    }
}