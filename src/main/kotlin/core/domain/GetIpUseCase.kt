package core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class GetIpUseCase() {
    suspend fun get(): String=withContext(Dispatchers.IO) {
        var publicIp = ""
        try {
            val url = URL("https://api.ipify.org")
            val reader = BufferedReader(InputStreamReader(url.openStream()))
            publicIp = reader.readLine().trim()
        } catch (_: Exception) { }
        publicIp
    }
}