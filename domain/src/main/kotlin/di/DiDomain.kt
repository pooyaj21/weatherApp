package di

import domain.*
import domain.GetCityWeatherUseCaseImpl
import domain.GetIpUseCaseImpl
import org.koin.dsl.module
import repository.*

val domainDiModule = module {

    includes(repositoryDiModule)

    factory<GetCityWeatherUseCase> { GetCityWeatherUseCaseImpl(get<WeatherRepository>()) }
    factory<GetPollutionUseCase> { GetPollutionUseCaseImpl(get<PollutionRepository>()) }
    factory<GetIpUseCase> { GetIpUseCaseImpl(get<IpRepository>()) }

    factory<GetWeatherBaseOnIpUseCase> {
        GetWeatherBaseOnIpUseCaseImpl(
            getIpUseCase = get<GetIpUseCase>(),
            getCityWeatherUseCase = get<GetCityWeatherUseCase>(),
            locationRepository = get<LocationRepository>()
        )
    }

}