package service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("ip") val ip: String,
    @SerialName("type") val type: String,
    @SerialName("continent_code") val continentCode: String,
    @SerialName("continent_name") val continentName: String,
    @SerialName("country_code") val countryCode: String,
    @SerialName("country_name") val countryName: String,
    @SerialName("region_code") val regionCode: String,
    @SerialName("region_name") val regionName: String,
    @SerialName("city") val city: String,
    @SerialName("zip") val zip: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("location") val location: Location
) {
    @Serializable
    data class Location(
        @SerialName("geoname_id") val geonameId: Int,
        @SerialName("capital") val capital: String,
        @SerialName("languages") val languages: List<Language>,
        @SerialName("country_flag") val countryFlag: String,
        @SerialName("country_flag_emoji") val countryFlagEmoji: String,
        @SerialName("country_flag_emoji_unicode") val countryFlagEmojiUnicode: String,
        @SerialName("calling_code") val callingCode: String,
        @SerialName("is_eu") val isEu: Boolean
    ) {
        @Serializable
        data class Language(
            @SerialName("code") val code: String,
            @SerialName("name") val name: String,
            @SerialName("native") val native: String
        )
    }
}
