package data

import di.RepositoryProvider
import model.City

class LocationRepository {
    suspend fun location(ip: String): City {
        val locationResponse = RepositoryProvider.providerLocationRepository().getData(ip)
        return City(name = locationResponse.city)
    }
}