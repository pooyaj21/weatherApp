package di

import service.di.ServiceProvider

object RepositoryProvider {
    fun providerWeatherRepository() = ServiceProvider.provideWeatherService()
    fun providerPollutionRepository() = ServiceProvider.providePollutionService()
    fun providerIpRepository() = ServiceProvider.provideIpService()
    fun providerLocationRepository() = ServiceProvider.provideLocationService()
}