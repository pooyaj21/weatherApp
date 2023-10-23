package service.di

import PollutionService
import WeatherService
import service.ServiceCreator
import service.service.LocationService

object ServiceProvider {

    private val weatherServiceCreator: ServiceCreator by lazy {
        ServiceCreator("https://api.openweathermap.org")
    }

    fun provideWeatherApiService() = weatherServiceCreator.create(WeatherService::class.java)
    fun providePollutionApiService() = weatherServiceCreator.create(PollutionService::class.java)
    fun provideLocationApiService() = ServiceCreator("http://api.ipstack.com").create(LocationService::class.java)

}