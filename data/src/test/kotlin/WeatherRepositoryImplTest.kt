import convertors.toTimeZone
import data.WeatherRepository
import data.WeatherRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Weather
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import service.response.WeatherResponse
import java.util.*
import kotlin.test.assertEquals

class WeatherRepositoryImplTest {

    private val mockWeatherService: WeatherService = mockk()
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        weatherService = mockWeatherService
    )

    @Test
    fun `is weather returns Weather`() = runTest {
        coEvery { mockWeatherService.getData(any(), any()) } returns WeatherResponse(
            coord = WeatherResponse.Coord(51.42, 35.64),
            weathers = listOf(WeatherResponse.Weather(100, "main", "description", "icond")),
            base = "base",
            main = WeatherResponse.Main(209.0, 209.1, 209.2, 209.3, 209.4, 209.5),
            visibility = 10000,
            wind = WeatherResponse.Wind(4.20, 120),
            clouds = WeatherResponse.Clouds(75),
            dt = 4,
            sys = WeatherResponse.Sys(2, 47737, "IR", 1698807342, 1798807342),
            timeZone = 12600,
            id = 112931,
            name = "Tehran",
            cod = 200
        )

        assertEquals(
            expected = Weather(
                cityWithLocation = Weather.CityWithLocation(
                    name = "Tehran",
                    id = 112931,
                    location = Weather.CityWithLocation.Location(35.64, 51.42 )
                ),
                status = Weather.Status("main", "description"),
                temperature = Weather.Temperature(209.0, 209.1),
                time = Weather.Time(
                    Date(4000),
                    Date(1698807342000),
                    Date(1798807342000),
                    12600.toTimeZone()
                ),
                windSpeed = 4.20,
                icon = "icond"
            ),
            actual = weatherRepository.weather("tehran")
        )
    }
}