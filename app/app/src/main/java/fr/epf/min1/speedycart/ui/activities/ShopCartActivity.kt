package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Client

class ShopCartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        val nameContainer = findViewById<TextView>(R.id.shop_cart_screen_name_container_textview)
        val adressContainer = findViewById<TextView>(R.id.shop_cart_screen_adress_container_textview)
        val productRecycler = findViewById<RecyclerView>(R.id.shop_cart_screen_product_list_recyclerview)
        val paymentButton = findViewById<Button>(R.id.shop_cart_screen_payment_button)

        val testClient = Client.generate1Client()

        nameContainer.text = "${testClient.firstname} ${testClient.lastname}"
    }
}