package data

import model.City
import service.di.ServiceProvider


interface LocationRepository{
    suspend fun location(ip: String): City
}
internal class LocationRepositoryImpl:LocationRepository {
    override suspend fun location(ip: String): City {
        val locationResponse = ServiceProvider.locationService().getData(ip)
        return City(name = locationResponse.city)
    }
}