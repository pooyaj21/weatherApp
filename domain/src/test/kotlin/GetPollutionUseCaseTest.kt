import domain.GetCityWeatherUseCaseImpl
import domain.GetPollutionUseCase
import domain.GetPollutionUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Pollution
import model.Weather
import kotlin.test.Test
import kotlin.test.assertEquals
import repository.PollutionRepository
import kotlin.test.assertEquals

class GetPollutionUseCaseTest {
    private val pollutionRepository:PollutionRepository= mockk()
    private val getPollutionUseCase:GetPollutionUseCase=GetPollutionUseCaseImpl(pollutionRepository)

    @Test
    fun `is getPollutionUseCase return Pollution`() = runTest {
        val expected: Result<Pollution> = mockk()
       val mockWeather: Weather= mockk()
        coEvery {
            getPollutionUseCase.get(any())
        } returns expected

        assertEquals(
            expected,
            getPollutionUseCase.get(mockWeather)
        )
    }

    @Test
    fun `is getPollutionUseCase return Pollution - Failure`() = runTest {
        val exception = RuntimeException()
        val mockWeather: Weather= mockk()
        coEvery {
            getPollutionUseCase.get(any())
        } throws exception

        assertEquals(
            Result.failure(exception),
            getPollutionUseCase.get(mockWeather)
        )
    }

}