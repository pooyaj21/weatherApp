import domain.GetCityWeatherUseCase
import domain.GetIpUseCase
import domain.GetWeatherBaseOnIpUseCase
import domain.GetWeatherBaseOnIpUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.City
import model.Weather
import repository.LocationRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWeatherBaseOnIpUseCaseTest {

    private val mockLocationRepository: LocationRepository = mockk()
    private val mockGetIpUseCase: GetIpUseCase = mockk()
    private val mockGetCityWeatherUseCase: GetCityWeatherUseCase = mockk()

    private val getWeatherBaseOnIpUseCase: GetWeatherBaseOnIpUseCase = GetWeatherBaseOnIpUseCaseImpl(
        getIpUseCase = mockGetIpUseCase,
        getCityWeatherUseCase = mockGetCityWeatherUseCase,
        locationRepository = mockLocationRepository
    )

    @Test
    fun `is get returns Ip`() = runTest {
        val mockWeatherResult: Result<Weather> = mockk()
        coEvery { mockGetIpUseCase.get() } returns "0.0.0.0"
        coEvery { mockLocationRepository.location(any()) } returns City("Tehran")
        coEvery { mockGetCityWeatherUseCase.get(any()) } returns mockWeatherResult
        assertEquals(
            expected = mockWeatherResult, actual = mockGetCityWeatherUseCase.get("Tehran")
        )
    }

    @Test
    fun `is get returns City`() = runTest {
        val mockWeatherResult: Result<Weather> = mockk()
        coEvery { mockGetIpUseCase.get() } returns "0.0.0.0"
        coEvery { mockLocationRepository.location("0.0.0.0") } returns City("Tehran")
        coEvery { mockGetCityWeatherUseCase.get(any()) } returns mockWeatherResult
        assertEquals(
            expected = mockWeatherResult, actual = mockGetCityWeatherUseCase.get("Tehran")
        )
    }

    @Test
    fun `is get returns Weather`() = runTest {
        val mockWeatherResult: Result<Weather> = mockk()
        coEvery { mockGetIpUseCase.get() } returns "0.0.0.0"
        coEvery { mockLocationRepository.location("0.0.0.0") } returns City("Tehran")
        coEvery { mockGetCityWeatherUseCase.get("Tehran") } returns mockWeatherResult
        assertEquals(
            expected = mockWeatherResult, actual = getWeatherBaseOnIpUseCase.get()
        )
    }

    @Test
    fun `is get Fails when getIpUseCase fails`() = runTest {
        val exception = RuntimeException()
        coEvery { mockGetIpUseCase.get() } throws exception
        assertEquals(
            expected = Result.failure(exception),
            actual = getWeatherBaseOnIpUseCase.get()
        )
    }

    @Test
    fun `is get Fails when location fails`() = runTest {
        val exception = RuntimeException()
        coEvery { mockGetIpUseCase.get() } returns "0.0.0.0"
        coEvery { mockLocationRepository.location("0.0.0.0") } throws exception
        assertEquals(
            expected = Result.failure(exception),
            actual = getWeatherBaseOnIpUseCase.get()
        )
    }

    @Test
    fun `is get Fails when weather fails`() = runTest {
        val exception = RuntimeException()
        coEvery { mockGetIpUseCase.get() } returns "0.0.0.0"
        coEvery { mockLocationRepository.location("0.0.0.0") } returns City("Tehran")
        coEvery { mockGetCityWeatherUseCase.get("Tehran") } returns  Result.failure(exception)
        assertEquals(
            expected = Result.failure(exception),
            actual = getWeatherBaseOnIpUseCase.get()
        )
    }
}