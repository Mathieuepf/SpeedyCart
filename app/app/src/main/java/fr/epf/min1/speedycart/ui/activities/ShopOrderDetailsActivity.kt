package fr.epf.min1.speedycart.ui.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.getCompleteName
import fr.epf.min1.speedycart.ui.adapters.SHOP_ORDER_EXTRA
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment

class ShopOrderDetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_order_details)

        // put info
        setInfoOnPage()

        // put navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setInfoOnPage() {
        val orderAtTextView = findViewById<TextView>(R.id.shop_order_details_order_at_textview)
        val nameDeliveryPersonTextView =
            findViewById<TextView>(R.id.shop_order_details_delivery_person_name_textview)
        val clientNameTextView =
            findViewById<TextView>(R.id.shop_order_details_client_name_textview)
        val deliveryPersonVehicle =
            findViewById<TextView>(R.id.shop_order_details_delivery_person_vehicle_textview)
        val priceTextView = findViewById<TextView>(R.id.shop_order_details_salary_textview)
        intent.extras?.apply {
            val orderDTO = this.getParcelable(SHOP_ORDER_EXTRA, OrderDTO::class.java)

            orderDTO?.apply {
                nameDeliveryPersonTextView.text =
                    this.order.delivery.deliveryPerson?.getCompleteName()
                        ?: "En attente d'un livreur"
                clientNameTextView.text = this.order.client.getCompleteName()
                deliveryPersonVehicle.text =
                    this.order.delivery.deliveryPerson?.vehicle ?: "En attente d'un livreur"
                orderAtTextView.text =
                    "Commandé à ${orderDTO.order.orderAt.hour} : ${orderDTO.order.orderAt.minute}"
                priceTextView.text =
                    "Remuneration:" + String.format(
                        "%.2f",
                        orderDTO.order.delivery.fee / 20 * 19
                    ) + " €"
            }
        }
    }
}