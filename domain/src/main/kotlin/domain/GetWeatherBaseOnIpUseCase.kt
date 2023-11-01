package domain


import model.Weather
import repository.LocationRepository

interface GetWeatherBaseOnIpUseCase {
    suspend fun get(): Result<Weather>
}

internal class GetWeatherBaseOnIpUseCaseImpl(
    private val getIpUseCase: GetIpUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val locationRepository: LocationRepository
) : GetWeatherBaseOnIpUseCase {

    override suspend fun get(): Result<Weather> {
        val cityResult = runCatching {
            val ip = getIpUseCase.get()
            locationRepository.location(ip).name
        }
        return if (cityResult.isSuccess) {
            getCityWeatherUseCase.get(cityResult.getOrThrow())
        } else {
            Result.failure(cityResult.exceptionOrNull()!!)
        }
    }
}