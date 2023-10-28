package service.di

import PollutionService
import WeatherService
import service.ServiceCreator
import service.service.IpService
import service.service.LocationService

object ServiceProvider {

    private val weatherServiceCreator: ServiceCreator by lazy {
        ServiceCreator("https://api.openweathermap.org")
    }

    fun provideWeatherService() = weatherServiceCreator.create(WeatherService::class.java)
    fun providePollutionService() = weatherServiceCreator.create(PollutionService::class.java)
    fun provideIpService() = ServiceCreator("https://api.ipify.org").create(IpService::class.java)
    fun provideLocationService() = ServiceCreator("http://ip-api.com/json/").create(LocationService::class.java)

}