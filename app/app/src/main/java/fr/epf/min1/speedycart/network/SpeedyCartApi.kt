package fr.epf.min1.speedycart.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "http://192.168.1.142:9090"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object  SpeedyCartApi{
    val retrofitService : SpeedyCartApiService by lazy {
        retrofit.create(SpeedyCartApiService::class.java)
    }
}