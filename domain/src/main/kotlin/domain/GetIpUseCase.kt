package domain

import data.IpRepository
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class GetIpUseCase {
    private val repository = IpRepository()
    suspend fun get(): String {
       return repository.ip()
    }

}