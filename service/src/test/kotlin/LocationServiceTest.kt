import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Test
import service.ServiceCreator
import service.response.LocationResponse
import service.service.LocationService
import kotlin.test.assertEquals

class LocationServiceTest {
    private val server: MockWebServer = MockWebServer()

    @Test
    fun `is getLocation return location`() = runBlocking {
        val locationResponse = LocationResponse(
            "address",
            "Tehran",
            "Iran",
            "IR",
            "isp",
            35.74,
            51.44,
            "",
            "1.1.1.1",
            "23",
            "Tehran",
            "success",
            "Asia/Tehran",
            ""
        )
        server.start()
        val baseUrl = server.url("/getLocation/").toString()

        val actual = ServiceCreator(baseUrl).create(LocationService::class.java)

        val response = MockResponse()
            .setBody(
                """
                {
                    "status": "success",
                    "country": "Iran",
                    "countryCode": "IR",
                    "region": "23",
                    "regionName": "Tehran",
                    "city": "Tehran",
                    "zip": "",
                    "lat": 35.74,
                    "lon": 51.44,
                    "timezone": "Asia/Tehran",
                    "isp": "isp",
                    "org": "",
                    "as": "address",
                    "query": "1.1.1.1"
                }
                """.trimIndent()
        )

        server.enqueue(response)

        assertEquals(locationResponse, actual.getData("1.1.1.1"))

        server.shutdown()

    }
}