import repository.LocationRepository
import repository.LocationRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import model.City
import org.junit.Test
import service.response.LocationResponse
import service.service.LocationService
import kotlin.test.assertEquals

class LocationRepositoryImplTest {
    private val mockLocationService: LocationService = mockk()
    private val locationRepository: LocationRepository = LocationRepositoryImpl(
        locationService = mockLocationService
    )

    @Test
    fun `is location return City`() = runTest {
        coEvery { mockLocationService.getData(any()) } returns LocationResponse(
            address = "address",
            city = "Tehran",
            country = "Iran",
            countryCode = "IR",
            isp = "isp",
            lat = 35.7,
            lon = 51.42,
            org = "org",
            query = "query",
            region = "region",
            regionName = "region name",
            status = "status",
            timezone = "timeZone",
            zip = "zip"
        )

        assertEquals(
            expected = City("Tehran"),
            actual = locationRepository.location("1.1.1.1")
        )
    }

}