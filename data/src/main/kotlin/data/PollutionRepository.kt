package data

import model.Pollution
import model.Weather
import service.di.ServiceProvider

interface PollutionRepository{
    suspend fun pollution(previousApi: Weather): Pollution
}

internal class PollutionRepositoryImpl:PollutionRepository {
    override suspend fun pollution(previousApi: Weather): Pollution {
        val pollutionResponse = ServiceProvider.pollutionService()
            .getData(
                lat = previousApi.cityWithLocation.location.lat.toString(),
                lon = previousApi.cityWithLocation.location.long.toString()
            )
        return Pollution(
            amountOfCo = pollutionResponse.list[0].components.co,
            amountOfNo2 = pollutionResponse.list[0].components.no2,
            amountOfNo = pollutionResponse.list[0].components.no,
            amountOfO3 = pollutionResponse.list[0].components.o3
        )
    }
}
