import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Test
import service.ServiceCreator
import service.response.PollutionResponse
import kotlin.test.assertEquals

class PollutionServiceTest {
    private val server: MockWebServer = MockWebServer()

    @Test
    fun `is getPollution return pollution`() = runBlocking {
        server.start()
        val baseUrl = server.url("/getIp/").toString()
        val pollutionResponse = PollutionResponse(
            PollutionResponse.Coord(
                35.69,
                51.42

            ), listOf(
                PollutionResponse.Pollution(
                    PollutionResponse.Main(3),
                    PollutionResponse.Components(
                        co = 534.06,
                        nh3 = 0.71,
                        no = 4.86,
                        no2 = 74.71,
                        o3 = 26.46,
                        pm10 = 23.9,
                        pm2_5 = 10.57,
                        so2 = 30.28,
                    ),
                    dt = 1698930797,
                )
            )
        )

        val actual = ServiceCreator(baseUrl).create(PollutionService::class.java)

        val response = MockResponse()
            .setBody(
                """
                {
                    "coord": {
                        "lon": 51.42,
                        "lat": 35.69
                    },
                    "list": [
                        {
                            "main": {
                                "aqi": 3
                            },
                            "components": {
                                "co": 534.06,
                                "no": 4.86,
                                "no2": 74.71,
                                "o3": 26.46,
                                "so2": 30.28,
                                "pm2_5": 10.57,
                                "pm10": 23.9,
                                "nh3": 0.71
                            },
                            "dt": 1698930797
                        }
                    ]
                }
                """.trimIndent()
            )

        server.enqueue(response)

        assertEquals(pollutionResponse, actual.getData("35.69", "51.42"))

        server.shutdown()

    }
}