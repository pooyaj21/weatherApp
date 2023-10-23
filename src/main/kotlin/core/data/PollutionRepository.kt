package core.data

import core.model.Pollution
import core.model.Weather
import core.service.PollutionService

class PollutionRepository(private val pollutionService: PollutionService) {
suspend fun pollution(previousApi :Weather):Pollution{
    val pollutionResponse =pollutionService.pollution(previousApi)
    return Pollution(
        co = pollutionResponse.list[0].components.co,
        no2 = pollutionResponse.list[0].components.no2,
        no = pollutionResponse.list[0].components.no,
        o3 = pollutionResponse.list[0].components.o3
    )
}
}
