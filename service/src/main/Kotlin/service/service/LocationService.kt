package service.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import service.response.LocationResponse

interface LocationService {
    @GET("/{ip}")
    suspend fun getData(
        @Path("ip") ip: String,
        @Query("access_key") accessKey: String="184e07193f66719f366544d1c50e549b"
    ): LocationResponse
}