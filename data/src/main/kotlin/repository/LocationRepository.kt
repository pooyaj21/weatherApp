package repository

import model.City
import service.service.LocationService


interface LocationRepository {
    suspend fun location(ip: String): City
}

internal class LocationRepositoryImpl(
    private val locationService: LocationService
) : LocationRepository {
    override suspend fun location(ip: String): City {
        val locationResponse = locationService.getData(ip)
        return City(name = locationResponse.city)
    }
}