import domain.GetCityWeatherUseCase
import domain.GetCityWeatherUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Weather
import repository.WeatherRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCityWeatherUseCaseTest {

    private val weatherRepository: WeatherRepository = mockk()
    private val getCityWeatherUseCase: GetCityWeatherUseCase = GetCityWeatherUseCaseImpl(weatherRepository)

    @Test
    fun `is getCityWeatherUseCase return Weather`() = runTest {
        val expected: Result<Weather> = mockk()
        coEvery {
            getCityWeatherUseCase.get(any())
        } returns expected

        assertEquals(
            expected,
            getCityWeatherUseCase.get("Tehran")
        )
    }

    @Test
    fun `is getCityWeatherUseCase return Weather - Failure`() = runTest {
        val exception = RuntimeException()
        coEvery { getCityWeatherUseCase.get("Tehran") } throws exception
        assertEquals(
            Result.failure(exception),
            getCityWeatherUseCase.get("Tehran")
        )
    }
}