package core.data

import PollutionApiService
import core.model.Pollution
import core.model.Weather

class PollutionRepository(private val pollutionService: PollutionApiService) {
suspend fun pollution(previousApi :Weather):Pollution{
    val pollutionResponse =pollutionService.getData(previousApi.lat.toString(),previousApi.lon.toString())
    return Pollution(
        co = pollutionResponse.list[0].components.co,
        no2 = pollutionResponse.list[0].components.no2,
        no = pollutionResponse.list[0].components.no,
        o3 = pollutionResponse.list[0].components.o3
    )
}
}
