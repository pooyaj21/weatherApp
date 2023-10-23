import retrofit2.http.GET
import retrofit2.http.Query
import service.response.PollutionResponse

interface PollutionService {
    @GET("/data/2.5/air_pollution")
    suspend fun getData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String="9a553da7016360c1f1e8f07fdf39012b"
    ): PollutionResponse
}