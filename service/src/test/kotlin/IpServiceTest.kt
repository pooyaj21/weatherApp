import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.jupiter.api.Test
import service.ServiceCreator
import service.service.IpService
import service.response.IpResponse
import kotlin.test.assertEquals

class IpServiceTest {

    private val server: MockWebServer = MockWebServer()

    @Test
    fun `is getIp return ip`() = runBlocking {
        server.start()
        val baseUrl = server.url("/getIp/").toString()

        val actual = ServiceCreator(baseUrl).create(IpService::class.java)

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setBody("""{ 
                "ip": "1.1.1.1"
                }""".trimIndent())

        server.enqueue(response)

        assertEquals("1.1.1.1", actual.getData().ip)

        server.shutdown()

    }
}
