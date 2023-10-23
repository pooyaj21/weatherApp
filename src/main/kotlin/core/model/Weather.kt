package core.model

data class Weather(
    val lat: Double,
    val lon: Double,
    val feelsLike: Double,
    val sunRise: Long,
    val sunSet: Long,
    val temp: Double,
    val date: Long,
    val description: String,
    val icon: String,
    val mainStatus: String,
    val name: String,
    val timeZone: Int,
    val speed: Double,
)