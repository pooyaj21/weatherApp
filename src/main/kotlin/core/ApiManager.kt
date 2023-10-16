package core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

object ApiManager {
    lateinit var weatherDataApi: ApiWeatherData
    suspend fun weatherApiCreator(cityName: String): ApiWeatherData {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val api: WeatherApiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(json.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
            .create(WeatherApiService::class.java)
        weatherDataApi=api.getData(cityName, "9a553da7016360c1f1e8f07fdf39012b")
        return api.getData(cityName, "9a553da7016360c1f1e8f07fdf39012b")
    }
}

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): ApiWeatherData
}