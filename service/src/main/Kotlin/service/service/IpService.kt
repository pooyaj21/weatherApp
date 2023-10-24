package service.service

import retrofit2.http.GET
import service.response.IpResponse


interface IpService {
    @GET("?format=json")
    suspend fun getData(): IpResponse
}