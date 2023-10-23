package core.data

import core.model.Location
import service.service.LocationApiService

class LocationRepository(private val locationService: LocationApiService) {
    suspend fun location(ip:String):Location{
        val locationService = locationService.getData(ip)
        return Location(city = locationService.city)

    }
}