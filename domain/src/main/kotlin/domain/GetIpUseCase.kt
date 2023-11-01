package domain

import di.RepositoryProvider

class GetIpUseCase {
    private val repository = RepositoryProvider.ipRepository()
    suspend fun get(): String {
        return repository.ip()
    }

}