package data

import di.RepositoryProvider
import model.Location

class LocationRepository {
    suspend fun location(ip: String): Location {
        val locationResponse = RepositoryProvider.providerLocationRepository().getData(ip)
        return Location(city = locationResponse.city)
    }
}