package ui.panel.airPollution

import core.service.response.PollutionResponse
import core.service.response.WeatherResponse
import core.domain.GetWeatherPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.model.UiState

class AirPollutionController(
    private val coroutineScope: CoroutineScope,
    private val getWeatherPollutionUseCase: GetWeatherPollutionUseCase
) {

    var callBack: ((UiState<PollutionResponse>) -> Unit)? = null
    fun pollution(response: WeatherResponse) {
        coroutineScope.launch {
            callBack?.invoke(UiState.Loading)
            var result = getWeatherPollutionUseCase.get(response)
            if (result.isSuccess) {
                callBack?.invoke(UiState.Data(result.getOrThrow()))
            } else {
                callBack?.invoke(UiState.Error(result.exceptionOrNull()))
            }
        }
    }

}