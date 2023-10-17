package ui.startedPanel

import core.ApiWeatherData
import domain.GetCityWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.UiState

class StartedPanelController(
    private val coroutineScope: CoroutineScope,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) {
    var callBack: ((UiState<ApiWeatherData>) -> Unit)? = null
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
}