package repository

import WeatherService
import convertor.toTimeZone
import model.Weather
import model.Weather.CityWithLocation.Location
import java.util.*


interface WeatherRepository {
    suspend fun weather(city: String): Weather
}

internal class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun weather(city: String): Weather {
        val weatherResponse = weatherService.getData(city)
        val location = Location(weatherResponse.coord.lat, weatherResponse.coord.lon)
        return Weather(
            cityWithLocation = Weather.CityWithLocation(weatherResponse.name, weatherResponse.id, location),
            status = Weather.Status(weatherResponse.weathers[0].main, weatherResponse.weathers[0].description),
            temperature = Weather.Temperature(weatherResponse.main.temp, weatherResponse.main.feelsLike),
            time = Weather.Time(
                Date(weatherResponse.dt * 1000),
                Date(weatherResponse.sys.sunrise * 1000),
                Date(weatherResponse.sys.sunset * 1000),
                weatherResponse.timeZone.toTimeZone()
            ),
            windSpeed = weatherResponse.wind.speed,
            icon = weatherResponse.weathers[0].icon
        )
    }
}