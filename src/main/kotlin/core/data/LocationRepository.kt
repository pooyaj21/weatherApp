package core.data

import core.model.Location
import core.service.LocationService

class LocationRepository(private val locationService:LocationService) {
    suspend fun location(ip:String):Location{
        val locationService = locationService.location(ip)
        return Location(city = locationService.city)

    }
}