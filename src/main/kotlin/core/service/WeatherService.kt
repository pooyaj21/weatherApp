package core.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import core.service.response.WeatherResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherService {
    suspend fun weather(cityName: String): WeatherResponse {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val api: WeatherApiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(WeatherApiService::class.java)

        return api.getData(cityName, "9a553da7016360c1f1e8f07fdf39012b")
    }

    interface WeatherApiService {
        @GET("/data/2.5/weather")
        suspend fun getData(
            @Query("q") cityName: String,
            @Query("appid") apiKey: String
        ): WeatherResponse

    }
}