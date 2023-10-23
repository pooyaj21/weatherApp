package ui.panel.loading

import core.model.Weather

class LoadingPanelController(private val response: Weather) {
    fun getDayOrNight(): String {
        return getIcon().last().toString()
    }
    fun getMainName():String{
        return response.mainStatus
    }
    fun getIcon():String{
        return response.icon
    }
}