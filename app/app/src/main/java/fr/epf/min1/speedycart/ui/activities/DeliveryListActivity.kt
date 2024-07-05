package fr.epf.min1.speedycart.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Delivery
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.DeliveryListFragment
import fr.epf.min1.speedycart.ui.fragments.EmptyDeliveryFragment
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.runBlocking

private const val TAG = "DeliveryListActivity"

class DeliveryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)

        // init delivery recycle view
        initDeliveries()

        // put navbar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_list_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private fun fetchDeliveries(): List<Delivery> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)
        val deliveries = Delivery.generateDeliveries()

        return deliveries.filter { d -> d.prepared }
    }

    private fun initDeliveries() {
        val deliveryList = fetchOrdersWaitingInfo()
        fetchOrdersWaitingInfo()

        if (deliveryList.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_list_screen_fragment_container, EmptyDeliveryFragment())
                .commit()
        } else {
            val fragment = DeliveryListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_list_screen_fragment_container, fragment)
                .commit()
            fragment.setDeliveryList(deliveryList)
        }
    }

    private fun fetchOrdersWaitingInfo(): List<OrderDTO> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getOrdersWaiting()
                if (response.isSuccessful && response.body() != null) {
                    val orderDTOS = response.body()!!
                    Log.d(TAG, "$orderDTOS")
                    orderDTOS
                } else {
                    Log.d(TAG, "call for product list is empty or unsuccessful")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emptyList()
            }
        }
    }
}