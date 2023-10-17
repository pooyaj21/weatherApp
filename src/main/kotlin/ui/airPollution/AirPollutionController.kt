package ui.airPollution

import core.ApiManager
import core.ApiPollutionData
import core.ApiWeatherData
import domain.GetCityWeatherUseCase
import domain.GetWeatherPollutionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ui.UiState

class AirPollutionController(
    private val coroutineScope: CoroutineScope,
    private val getWeatherPollutionUseCase: GetWeatherPollutionUseCase
) {

    var callBack: ((UiState<ApiPollutionData>) -> Unit)? = null
    fun pollution(response: ApiWeatherData) {
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