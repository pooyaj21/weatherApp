package service.di

import PollutionService
import WeatherService
import org.koin.dsl.module
import service.ServiceCreator
import service.service.IpService
import service.service.LocationService

val serviceDiModule = module {

    single { ServiceCreator("https://api.openweathermap.org") }

    factory { get<ServiceCreator>().create(WeatherService::class.java) }
    factory { get<ServiceCreator>().create(PollutionService::class.java) }
    factory { ServiceCreator("https://api.ipify.org").create(IpService::class.java) }
    factory { ServiceCreator("http://ip-api.com/json/").create(LocationService::class.java) }

}