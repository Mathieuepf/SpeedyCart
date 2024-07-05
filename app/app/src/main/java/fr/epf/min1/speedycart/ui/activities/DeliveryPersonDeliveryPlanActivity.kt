package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.MainActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.data.getCompleteAddress
import fr.epf.min1.speedycart.data.getCompleteName
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.EmptyProductMessageFragment
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import fr.epf.min1.speedycart.ui.fragments.ProductListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val TAG = "DeliveryPersonDeliveryPlanActivity"

class DeliveryPersonDeliveryPlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_person_delivery_plan)

        lifecycleScope.launch {
            // find orderDTO
            val orderDTO = fetchOrderDTOS()[0]

            // put right info on page
            setInfoOnPage(orderDTO)

            // init product list
            val products = orderDTO.products.map { it.product }
            initProducts(products)

            // set Button delivery delivered
            setDeliveredButton(orderDTO)
        }

        // put navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.delivery_person_delivery_navbar_fragment_container,
                    NavigationBarFragment()
                )
                .commit()
        }


    }

    private fun setDeliveredButton(orderDTO: OrderDTO) {
        val deliveredButton = findViewById<Button>(R.id.delivery_person_delivery_delivered_button)
        deliveredButton.click {
            runBlocking {
                val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)
                orderDTO.order.delivery.id?.let { it1 -> clientService.setDeliveredDelivery(it1) }
                val intent =
                    Intent(this@DeliveryPersonDeliveryPlanActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private suspend fun fetchOrderDTOS(): List<OrderDTO> {
        val repository = AppRepository(application)
        val users = withContext(Dispatchers.IO) {
            repository.getUser()
        }
        val id = users[0].typeUserId
        val orderDTO = fetchDeliveryByDeliveryPersonInfo(id)
        return orderDTO
    }

    private fun setInfoOnPage(orderDTO: OrderDTO) {
        val shopNameTextView =
            findViewById<TextView>(R.id.delivery_person_delivery_shop_name_textview)
        val clientNameTextView =
            findViewById<TextView>(R.id.delivery_person_delivery_client_name_textview)
        val shopAddressTextView =
            findViewById<TextView>(R.id.delivery_person_delivery_shop_address_textview)
        val salaryTextView = findViewById<TextView>(R.id.delivery_person_delivery_salary_textview)
        val statusTextView =
            findViewById<TextView>(R.id.delivery_person_delivery_status_order_textview)

        orderDTO.apply {
            val product = this.products[0].product
            val shop = product.shop
            shopNameTextView.text = shop.name
            shopAddressTextView.text = shop.getCompleteAddress()
            clientNameTextView.text = this.order.client.getCompleteName()
            salaryTextView.text =
                "Remuneration : " + String.format("%.2f", this.order.delivery.fee / 20) + " €"
            statusTextView.text = if (this.order.delivery.prepared) {
                "Prete"
            } else {
                "En cours"
            }
        }
    }

    private fun initProducts(productList: List<Product>) {
        if (productList.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.delivery_person_delivery_screen_fragment_container,
                    EmptyProductMessageFragment()
                )
                .commit()
        } else {
            val fragment = ProductListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.delivery_person_delivery_screen_fragment_container, fragment)
                .commit()
            fragment.setProductList(productList)
        }
    }

    private fun fetchDeliveryByDeliveryPersonInfo(id: Long): List<OrderDTO> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getDeliveryWaitingByDeliveryPerson(id)
                if (response.isSuccessful && response.body() != null) {
                    val orderDTOS = response.body()!!
                    Log.d(TAG, "$orderDTOS")
                    orderDTOS
                } else {
                    Log.d(
                        TAG,
                        "call for waiting delivery list is empty or unsuccessful"
                    )
                    emptyList()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emptyList()
            }
        }
    }
}