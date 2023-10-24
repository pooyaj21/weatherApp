package domain

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class GetIpUseCase {
    fun get(): String{
        var publicIp = ""
        try {
            val url = URL("https://api.ipify.org")
            val reader = BufferedReader(InputStreamReader(url.openStream()))
            publicIp = reader.readLine().trim()
        } catch (_: Exception) { }
        return publicIp
    }

}