package service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("as") val address: String,
    @SerialName("city") val city: String,
    @SerialName("country") val country: String,
    @SerialName("countryCode") val countryCode: String,
    @SerialName("isp") val isp: String,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("org") val org: String,
    @SerialName("query") val query: String,
    @SerialName("region") val region: String,
    @SerialName("regionName") val regionName: String,
    @SerialName("status") val status: String,
    @SerialName("timezone") val timezone: String,
    @SerialName("zip") val zip: String
)