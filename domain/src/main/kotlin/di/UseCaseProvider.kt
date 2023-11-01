package di

import domain.*
import domain.GetCityWeatherUseCaseImpl
import domain.GetIpUseCaseImpl

object UseCaseProvider {
    fun cityWeatherUseCase():GetCityWeatherUseCase = GetCityWeatherUseCaseImpl(RepositoryProvider.weatherRepository())
    fun ipUseCase():GetIpUseCase = GetIpUseCaseImpl(RepositoryProvider.ipRepository())
    fun pollutionUseCase():GetPollutionUseCase = GetPollutionUseCaseImpl(RepositoryProvider.pollutionRepository())
    fun ipWeatherUseCase(): GetWeatherBaseOnIpUseCase = GetWeatherBaseOnIpUseCaseImpl(ipUseCase(), cityWeatherUseCase(),RepositoryProvider.locationRepository())


}