package service.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import service.response.LocationResponse

interface LocationService {
    @GET("{ip}")
    suspend fun getData(
        @Path("ip") ip: String,
    ): LocationResponse
}