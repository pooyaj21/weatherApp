package core.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import core.model.Weather
import core.service.response.PollutionResponse
import core.service.response.WeatherResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class PollutionService{
     suspend fun pollution(previousApi: Weather): PollutionResponse {
         val json = Json {
             ignoreUnknownKeys = true
         }

         val api: PollutionApiService = Retrofit.Builder()
             .baseUrl("https://api.openweathermap.org")
             .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
             .build()
             .create(PollutionApiService::class.java)

         return api.getData(
             previousApi.lat.toString(),
             previousApi.lon.toString(),
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
         ): PollutionResponse
     }
 }
