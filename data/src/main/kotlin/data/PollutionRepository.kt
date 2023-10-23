package data

import PollutionApiService
import model.Pollution
import model.Weather
import service.ServiceProvider

class PollutionRepository() {
    suspend fun pollution(previousApi: Weather): Pollution {
        val pollutionResponse =
            ServiceProvider.providePollutionApiService().getData(
                lat = previousApi.lat.toString(),
                lon = previousApi.lon.toString()
            )
        return Pollution(
            co = pollutionResponse.list[0].components.co,
            no2 = pollutionResponse.list[0].components.no2,
            no = pollutionResponse.list[0].components.no,
            o3 = pollutionResponse.list[0].components.o3
        )
    }
}
