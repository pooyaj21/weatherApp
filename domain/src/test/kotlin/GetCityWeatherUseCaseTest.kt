import domain.GetCityWeatherUseCase
import domain.GetCityWeatherUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Weather
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import repository.WeatherRepository

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
}