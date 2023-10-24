package service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpResponse(
    @SerialName("ip") val ip: String
)