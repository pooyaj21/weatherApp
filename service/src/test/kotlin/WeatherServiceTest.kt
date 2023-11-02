import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Test
import service.ServiceCreator
import service.response.WeatherResponse
import kotlin.test.assertEquals

class WeatherServiceTest {

    private val server: MockWebServer = MockWebServer()

    @Test
    fun `is getWeather return Weather`() = runBlocking {
        server.start()
        val baseUrl = server.url("/getIp/").toString()
        val weatherResponse = WeatherResponse(
            WeatherResponse.Coord(51.42, 35.69),
            listOf(WeatherResponse.Weather(804, "Clouds", "clear", "04d")),
            "base",
            WeatherResponse.Main(209.0, 210.0, 211.0, 212.0, 1000.0, 21.0),
            7000,
            WeatherResponse.Wind(5.1, 230),
            WeatherResponse.Clouds(100),
            1698931233,
            WeatherResponse.Sys(2, 47737, "IR", 1698893801, 1698932339),
            12600,
            112931,
            "Tehran",
            200
        )

        val weatherService = ServiceCreator(baseUrl).create(WeatherService::class.java)

        val response = MockResponse()
            .setBody(
                """
                {
                    "coord": {
                        "lon": 51.42,
                        "lat": 35.69
                    },
                    "weather": [
                        {
                            "id": 804,
                            "main": "Clouds",
                            "description": "clear",
                            "icon": "04d"
                        }
                    ],
                    "base": "base",
                    "main": {
                        "temp": 209.0,
                        "feels_like": 210.0,
                        "temp_min": 211.0,
                        "temp_max": 212.0,
                        "pressure": 1000.0,
                        "humidity": 21.0
                    },
                    "visibility": 7000,
                    "wind": {
                        "speed": 5.1,
                        "deg": 230
                    },
                    "clouds": {
                        "all": 100
                    },
                    "dt": 1698931233,
                    "sys": {
                        "type": 2,
                        "id": 47737,
                        "country": "IR",
                        "sunrise": 1698893801,
                        "sunset": 1698932339
                    },
                    "timezone": 12600,
                    "id": 112931,
                    "name": "Tehran",
                    "cod": 200
                }
                """.trimIndent()
            )

        server.enqueue(response)

        assertEquals(weatherResponse, weatherService.getData("Tehran"))

        server.shutdown()

    }
}