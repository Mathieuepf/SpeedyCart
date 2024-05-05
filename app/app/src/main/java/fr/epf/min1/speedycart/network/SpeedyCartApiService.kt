package fr.epf.min1.speedycart.network

import retrofit2.http.GET

interface SpeedyCartApiService {
    @GET("/Clients")
    suspend fun getClients() : String
}
