import retrofit2.http.GET
import retrofit2.http.Query
import service.response.WeatherResponse

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "9a553da7016360c1f1e8f07fdf39012b"
    ): WeatherResponse

}