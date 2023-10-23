package data

import model.Location
import service.ServiceProvider
import service.service.LocationApiService

class LocationRepository() {
    suspend fun location(ip: String): Location {
        val locationResponse = ServiceProvider.provideLocationApiService().getData(ip)
        return Location(city = locationResponse.city)

    }
}