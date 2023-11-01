package di

import data.*

object RepositoryProvider {
    fun weatherRepository(): WeatherRepository = WeatherRepositoryImpl()
    fun pollutionRepository():PollutionRepository = PollutionRepositoryImpl()
    fun ipRepository(): IpRepository = IpRepositoryImpl()
    fun locationRepository(): LocationRepository = LocationRepositoryImpl()
}