package model

import java.util.*

data class Weather(
    val cityWithLocation: CityWithLocation,
    val status: Status,
    val temperature: Temperature,
    val time: Time,
    val windSpeed: Double,
    val icon: String
) {

    data class Temperature(val realKelvin: Double, val feelsLikeKelvin: Double) {
        val realCentigrade: Int = realKelvin.toCentigrade().toInt()
        val feelsLikeCentigrade: Int = feelsLikeKelvin.toCentigrade().toInt()
        private fun Double.toCentigrade(): Double = this-273.15
    }
    data class CityWithLocation(override val name: String, val location: Location) : City(name) {
        data class Location(val lat: Double, val long: Double)
    }

    data class Status(val short: String, val long: String)

    data class Time(val current: Date, val sunrise: Date, val sunSet: Date, val timeZone: TimeZone)
}