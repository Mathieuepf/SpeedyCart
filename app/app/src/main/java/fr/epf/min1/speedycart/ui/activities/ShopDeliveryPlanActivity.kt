package fr.epf.min1.speedycart.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import fr.epf.min1.speedycart.ui.fragments.ShopOrderEmptyFragment
import fr.epf.min1.speedycart.ui.fragments.ShopOrderListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "ShopDeliveryPlanActivity "

class ShopDeliveryPlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_delivery_plan)

        // put navbar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.shop_delivery_plan_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }

        // init order on page
        lifecycleScope.launch {
            val id = fetchShopId()
            val orderDTOs = fetchShopOrderInfo(id)
            initOrders(orderDTOs)
        }
    }

    private fun fetchShopOrderInfo(id: Long): List<OrderDTO> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getOrderWaitingByShop(id)
                if (response.isSuccessful && response.body() != null) {
                    val shopList = response.body()!!
                    Log.d(TAG, "$shopList")
                    shopList
                } else {
                    Log.d(TAG, "call for shop order list is empty or unsuccessful")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emptyList()
            }
        }
    }

    private suspend fun fetchShopId(): Long {
        val repository = AppRepository(application)
        val users = withContext(Dispatchers.IO) {
            repository.getUser()
        }
        return users[0].typeUserId
    }

    private fun initOrders(orderList: List<OrderDTO>) {
        if (orderList.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.shop_delivery_plan_fragment_container,
                    ShopOrderEmptyFragment()
                )
                .commit()
        } else {
            val fragment = ShopOrderListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.shop_delivery_plan_fragment_container, fragment)
                .commit()
            fragment.setOrderList(orderList)
        }
    }
}