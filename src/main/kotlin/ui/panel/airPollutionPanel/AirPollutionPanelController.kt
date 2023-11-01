package ui.panel.airPollutionPanel

import domain.GetPollutionUseCase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import model.Pollution
import model.Weather
import ui.model.UiState

class AirPollutionPanelController(
    private val coroutineScope: CoroutineScope,
    private val getWeatherPollutionUseCase: GetPollutionUseCase
) {
    var callBack: ((UiState<Pollution>) -> Unit)? = null
    fun pollution(response: Weather) {
        coroutineScope.launch {
            callBack?.invoke(UiState.Loading)
            val result = getWeatherPollutionUseCase.get(response)
            if (result.isSuccess) {
                callBack?.invoke(UiState.Data(result.getOrThrow()))
            } else {
                callBack?.invoke(UiState.Error(result.exceptionOrNull()))
            }
        }
    }

}