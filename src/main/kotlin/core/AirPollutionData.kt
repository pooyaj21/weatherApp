package core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ApiPollutionData(
    @SerialName("coord") val coord: Coord,
    @SerialName("list") val list: List<Pollution>
) {
    @Serializable
    data class Coord(
        @SerialName("lat") val lat: Double,
        @SerialName("lon") val lon: Double
    )

    @Serializable
    data class Pollution(
        @SerialName("main") val main: Main,
        @SerialName("components") val components: Components,
        @SerialName("dt") val dt: Int
    )

    @Serializable
    data class Components(
        @SerialName("co") val co: Double,
        @SerialName("nh3") val nh3: Double,
        @SerialName("no") val no: Double,
        @SerialName("no2") val no2: Double,
        @SerialName("o3") val o3: Double,
        @SerialName("pm10") val pm10: Double,
        @SerialName("pm2_5") val pm2_5: Double,
        @SerialName("so2") val so2: Double
    )

    @Serializable
    data class Main(
        @SerialName("aqi") val aqi: Int
    )

}