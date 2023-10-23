package ui.panel.airPollution

import core.domain.GetWeatherPollutionUseCase
import model.Pollution
import model.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.model.UiState

class AirPollutionController(
    private val coroutineScope: CoroutineScope,
    private val getWeatherPollutionUseCase: GetWeatherPollutionUseCase
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