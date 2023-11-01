import domain.GetCityWeatherUseCaseImpl
import domain.GetPollutionUseCase
import domain.GetPollutionUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Pollution
import model.Weather
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import repository.PollutionRepository

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

        Assertions.assertEquals(
            expected,
            getPollutionUseCase.get(mockWeather)
        )
    }



}