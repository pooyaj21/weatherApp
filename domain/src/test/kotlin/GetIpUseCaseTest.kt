import domain.GetIpUseCase
import domain.GetIpUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import repository.IpRepository

class GetIpUseCaseTest {
    private val ipRepository: IpRepository = mockk()
    private val getIpUseCase: GetIpUseCase = GetIpUseCaseImpl(ipRepository)

    @Test
    fun `is getIpUseCase return Ip String `() = runTest {
        val expected: String = "1.1.1.1"
        coEvery {
            getIpUseCase.get()
        } returns expected

        assertEquals(
            expected,
            getIpUseCase.get()
        )
    }


}