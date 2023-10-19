package core

import ApiLocationData
import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiManager {
    val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun weatherApiCreatorBaseOnCity(cityName: String): ApiWeatherData {
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
        ): ApiWeatherData

    }


    suspend fun pollutionApiCreator(previousApi: ApiWeatherData): ApiPollutionData {
        val api: PollutionApiService = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(PollutionApiService::class.java)

        return api.getData(
            previousApi.coord.lat.toString(),
            previousApi.coord.lon.toString(),
            "9a553da7016360c1f1e8f07fdf39012b"
        )                                                   //With retrofit


//        val client = OkHttpClient()
//        val gson = Gson()
//
//        val url = "https://api.openweathermap.org/data/2.5/air_pollution" +
//                "?lat=${previousApi.coord.lat}&lon=${previousApi.coord.lon}&appid=9a553da7016360c1f1e8f07fdf39012b"
//
//        val api = Request.Builder()
//            .url(url)
//            .build()
//
//        val response = client.newCall(api).execute()
//        return if (response.isSuccessful) {
//            val json = response.body?.string() ?: ""
//            gson.fromJson(json, ApiPollutionData::class.java)
//        } else {
//            throw Exception("Error: ${response.code}")
//        }                                                     //with okHttp


    }

    interface PollutionApiService {
        @GET("/data/2.5/air_pollution")
        suspend fun getData(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") apiKey: String
        ): ApiPollutionData
    }


    suspend fun weatherApiCreatorBaseOnLocation(ip: String): ApiWeatherData {
        val api: LocationApiService = Retrofit.Builder()
            .baseUrl("http://api.ipstack.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(LocationApiService::class.java)

        val city = api.getData(ip, "184e07193f66719f366544d1c50e549b").city
        return weatherApiCreatorBaseOnCity(city)
    }

    interface LocationApiService {
        @GET("/{ip}")
        suspend fun getData(
            @Path("ip") ip: String,
            @Query("access_key") accessKey: String
        ): ApiLocationData
    }
}

