import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import service.ServiceCreator
import service.service.IpService
import service.response.IpResponse
import kotlin.test.assertEquals

class ServiceTests {

    private val server: MockWebServer = MockWebServer()


    @Test
    fun `is getIp return ip`() = runBlocking {
        server.start()
        val baseUrl = server.url("/").toString()

        val mockIpService: IpService = mockk()
        coEvery { mockIpService.getData() } returns IpResponse("1.1.1.1")
        val actual = ServiceCreator(baseUrl).create(mockIpService::class.java)

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setBody("1.1.1.1")

        server.enqueue(response)


        assertEquals("1.1.1.1", actual.getData().ip)

        server.shutdown()

    }
}
