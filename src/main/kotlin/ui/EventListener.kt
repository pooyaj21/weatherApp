package ui

import core.ApiWeatherData

fun interface EventListener {
    fun nextPage(response: ApiWeatherData)
}