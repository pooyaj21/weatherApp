import convertor.toTimeZone
import repository.PollutionRepository
import repository.PollutionRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.Pollution
import model.Weather
import org.junit.jupiter.api.Test
import service.response.PollutionResponse
import java.util.*
import kotlin.test.assertEquals

class PollutionRepositoryImplTest {
    private val mockPollutionService: PollutionService = mockk()
    private val pollutionRepository: PollutionRepository = PollutionRepositoryImpl(
        pollutionService = mockPollutionService
    )

    @Test
    fun `is ip return IpsString`() = runTest {
        coEvery { mockPollutionService.getData(any(), any()) } returns PollutionResponse(
            PollutionResponse.Coord(
                lat = 35.7,
                lon = 51.42
            ), listOf(
                PollutionResponse.Pollution(
                    main = PollutionResponse.Main(aqi = 1),
                    components = PollutionResponse.Components(
                        co = 1.2,
                        nh3 = 2.3,
                        no = 3.4,
                        no2 = 4.5,
                        o3 = 5.6,
                        pm10 = 6.7,
                        pm2_5 = 7.8,
                        so2 = 9.7
                    ),
                    dt = 12
                )
            )
        )

        assertEquals(
            expected = Pollution(1.2, 4.5, 3.4, 5.6),
            actual = pollutionRepository.pollution(
                Weather(
                    cityWithLocation = Weather.CityWithLocation(
                        name = "Tehran",
                        id = 112931,
                        location = Weather.CityWithLocation.Location(35.64, 51.42)
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
                )
            )
        )
    }
}