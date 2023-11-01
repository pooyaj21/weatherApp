package di

import repository.*
import service.di.ServiceProvider

object RepositoryProvider {
    fun weatherRepository(): WeatherRepository = WeatherRepositoryImpl(ServiceProvider.weatherService())
    fun pollutionRepository():PollutionRepository = PollutionRepositoryImpl(ServiceProvider.pollutionService())
    fun ipRepository(): IpRepository = IpRepositoryImpl(ServiceProvider.ipService())
    fun locationRepository(): LocationRepository = LocationRepositoryImpl(ServiceProvider.locationService())
}