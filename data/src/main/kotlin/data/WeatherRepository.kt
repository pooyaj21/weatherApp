package data

import WeatherApiService
import model.Weather
import service.ServiceProvider

class WeatherRepository() {

    suspend fun weather(city: String): Weather {
        val weatherResponse = ServiceProvider.provideWeatherApiService().getData(city)
        return Weather(
            lat = weatherResponse.coord.lat,
            lon = weatherResponse.coord.lon,
            feelsLike = weatherResponse.main.feelsLike,
            sunRise = weatherResponse.sys.sunrise,
            sunSet = weatherResponse.sys.sunset,
            temp = weatherResponse.main.temp,
            date = weatherResponse.dt,
            description = weatherResponse.weathers[0].description,
            icon = weatherResponse.weathers[0].icon,
            mainStatus = weatherResponse.weathers[0].main,
            name = weatherResponse.name,
            timeZone = weatherResponse.timeZone,
            speed = weatherResponse.wind.speed
        )
    }
}