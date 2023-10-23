package core.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import core.Manager.ApiManager
import core.service.response.LocationResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class LocationService {
    suspend fun location(ip: String): LocationResponse {
        val api: LocationApiService = Retrofit.Builder()
            .baseUrl("http://api.ipstack.com")
            .addConverterFactory(ApiManager.json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(LocationApiService::class.java)

        return api.getData(ip, "184e07193f66719f366544d1c50e549b")
    }

    interface LocationApiService {
        @GET("/{ip}")
        suspend fun getData(
            @Path("ip") ip: String,
            @Query("access_key") accessKey: String
        ): LocationResponse
    }
}