package data

import di.RepositoryProvider
import model.Pollution
import model.Weather

class PollutionRepository {
    suspend fun pollution(previousApi: Weather): Pollution {
        val pollutionResponse = RepositoryProvider.providerPollutionRepository()
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
