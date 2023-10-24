package domain

import data.IpRepository

class GetIpUseCase {
    private val repository = IpRepository()
    suspend fun get(): String {
        return repository.ip()
    }

}