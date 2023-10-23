package core.domain

import core.data.LocationRepository
import core.model.Location

class GetCityBaseOnIp(private val locationRepository: LocationRepository) {
    suspend fun get(): Result<Location> {
        val ip = GetIp().get()
        return runCatching { locationRepository.location(ip) }
    }
}