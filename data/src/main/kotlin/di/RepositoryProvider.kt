package di

import service.di.ServiceProvider

object RepositoryProvider {
    fun providerWeatherRepository() = ServiceProvider.provideWeatherApiService()
    fun providerPollutionRepository() = ServiceProvider.providePollutionApiService()
    fun providerLocationRepository() = ServiceProvider.provideLocationApiService()
}