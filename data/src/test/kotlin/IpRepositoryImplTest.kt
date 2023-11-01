import data.IpRepository
import data.IpRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import service.response.IpResponse
import service.service.IpService
import kotlin.test.assertEquals

class IpRepositoryImplTest {

    private val mockIpService: IpService = mockk()
    private val ipRepository : IpRepository = IpRepositoryImpl(
        ipService = mockIpService
    )

    @Test
    fun `is ip return IpsString`()= runTest {
        coEvery{mockIpService.getData()} returns IpResponse("1.1.1.1")

        assertEquals(
            expected = "1.1.1.1",
            actual = ipRepository.ip()
        )
    }
}
