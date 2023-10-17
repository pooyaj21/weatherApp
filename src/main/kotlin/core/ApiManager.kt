package core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

object ApiManager {
    lateinit var weatherDataApi: ApiWeatherData
    lateinit var pollutionDataApi: ApiPollutionData

    suspend fun weatherApiCreator(cityName: String): ApiWeatherData {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val api: WeatherApiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(WeatherApiService::class.java)
        weatherDataApi=api.getData(cityName, "9a553da7016360c1f1e8f07fdf39012b")
        return api.getData(cityName, "9a553da7016360c1f1e8f07fdf39012b")
    }
    class MyInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            // Add your custom header here
            val modifiedRequest = request.newBuilder()
                .addHeader("Mamad", "44")
                .build()
            return chain.proceed(modifiedRequest)
        }
    }
    suspend fun pollutionApiCreator(previousApi: ApiWeatherData): ApiPollutionData {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val api: PollutionApiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(PollutionApiService::class.java)

        pollutionDataApi=api.getData(previousApi.coord.lat.toString(), previousApi.coord.lon.toString(), "9a553da7016360c1f1e8f07fdf39012b")
        return api.getData(previousApi.coord.lat.toString(), previousApi.coord.lon.toString(), "9a553da7016360c1f1e8f07fdf39012b")
    }


}

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): ApiWeatherData

}


interface PollutionApiService{
    @GET("/data/2.5/air_pollution")
    suspend fun getData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String
    ): ApiPollutionData
}