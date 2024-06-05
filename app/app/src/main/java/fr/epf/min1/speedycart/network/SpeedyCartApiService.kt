package fr.epf.min1.speedycart.network

import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpeedyCartApiService {
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/shops")
    suspend fun getShops(): Response<List<Shop>>

    @GET("shop/{id}/products")
    suspend fun getProductsByShop(@Path("id") id: Long): Response<List<Product>>
}
