package ui.panel.startedPanel

import core.domain.GetWeatherBaseOnIpUseCase
import core.domain.GetCityWeatherUseCase
import core.model.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.model.UiState

class StartedPanelController(
    private val coroutineScope: CoroutineScope,
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val getCityBaseOnIp: GetWeatherBaseOnIpUseCase
) {
    var callBack: ((UiState<Weather>) -> Unit)? = null
    fun city(city: String) {
        coroutineScope.launch {
            callBack?.invoke(UiState.Loading)
            val result = getCityWeatherUseCase.get(city)
            if (result.isSuccess) {
                callBack?.invoke(UiState.Data(result.getOrThrow()))
            } else {
                callBack?.invoke(UiState.Error(result.exceptionOrNull()))
            }
        }
    }

    fun city() {
        coroutineScope.launch {
            callBack?.invoke(UiState.Loading)
            val result = getCityBaseOnIp.get()
            if (result.isSuccess) {
                callBack?.invoke(UiState.Data(result.getOrThrow()))
            } else {
                callBack?.invoke(UiState.Error(result.exceptionOrNull()))
            }
        }
    }
}