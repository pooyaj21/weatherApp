package di

import PollutionService
import WeatherService
import org.koin.dsl.module
import repository.*
import service.di.serviceDiModule
import service.service.IpService
import service.service.LocationService


val repositoryDiModule = module {
    includes(serviceDiModule)

    factory<WeatherRepository> { WeatherRepositoryImpl(get<WeatherService>()) }
    factory<PollutionRepository> { PollutionRepositoryImpl(get<PollutionService>()) }
    factory<IpRepository> { IpRepositoryImpl(get<IpService>()) }
    factory<LocationRepository> { LocationRepositoryImpl(get<LocationService>()) }

}