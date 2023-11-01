package domain


import di.RepositoryProvider
import model.Weather

class GetWeatherBaseOnIpUseCase(
    private val getIp: GetIpUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) {
    private val repository = RepositoryProvider.locationRepository()

    suspend fun get(): Result<Weather> {
        val ip = getIp.get()
        val city = repository.location(ip).name
        return getCityWeatherUseCase.get(city)
    }
}