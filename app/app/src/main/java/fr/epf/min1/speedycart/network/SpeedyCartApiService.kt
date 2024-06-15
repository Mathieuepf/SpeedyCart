package fr.epf.min1.speedycart.network

import fr.epf.min1.speedycart.data.DeliveryPerson
import fr.epf.min1.speedycart.data.LoginDTO
import fr.epf.min1.speedycart.data.Order
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SpeedyCartApiService {
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/user/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<User>

    @GET("/shop/{id}")
    suspend fun getShopById(@Path("id") id: Long): Response<Shop>

    @GET("/shops")
    suspend fun getShops(): Response<List<Shop>>

    @GET("shop/{id}/products")
    suspend fun getProductsByShop(@Path("id") id: Long): Response<List<Product>>

    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>

    @POST("/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<User>

    @GET("/orders/waiting")
    suspend fun getOrdersWaiting(): Response<List<OrderDTO>>

    @POST("/order")
    suspend fun addOrder(@Body orderDTO: OrderDTO): Response<Order>

    @GET("/delivery/waiting/deliveryperson/{id}")
    suspend fun getDeliveryWaitingByDeliveryPerson(@Path("id") id: Long): Response<List<OrderDTO>>

    @PATCH("/delivery/{id}/delivered")
    suspend fun setDeliveredDelivery(@Path("id") id: Long)

    @PATCH("/delivery/{id}/deliveryPerson")
    suspend fun setDeliveryPersonDelivery(
        @Path("id") id: Long,
        @Body deliveryPerson: DeliveryPerson
    )

    @GET("/orders/shop/{id}")
    suspend fun getOrderWaitingByShop(@Path("id") id: Long): Response<List<OrderDTO>>
}
