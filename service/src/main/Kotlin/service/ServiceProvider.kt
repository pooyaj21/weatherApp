package service

import PollutionApiService
import WeatherApiService
import service.service.LocationApiService

object ServiceProvider {

    private val weatherServiceCreator: ServiceCreator by lazy {
        ServiceCreator("https://api.openweathermap.org")
    }

    fun provideWeatherApiService() = weatherServiceCreator.create(WeatherApiService::class.java)
    fun providePollutionApiService() = weatherServiceCreator.create(PollutionApiService::class.java)
    fun provideLocationApiService() = ServiceCreator("http://api.ipstack.com").create(LocationApiService::class.java)

}