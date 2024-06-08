package fr.epf.min1.speedycart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.ui.adapters.DELETE_CART_EXTRA
import fr.epf.min1.speedycart.ui.adapters.ProductCartAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ShopCartActivity"

class ShopCartActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)

        val nameContainer = findViewById<TextView>(R.id.shop_cart_screen_name_container_textview)
        val adressContainer = findViewById<TextView>(R.id.shop_cart_screen_adress_container_textview)
        val productRecycler = findViewById<RecyclerView>(R.id.shop_cart_screen_product_list_recyclerview)
        val paymentButton = findViewById<Button>(R.id.shop_cart_screen_payment_button)

        val testClient = Client.generate1Client()

        nameContainer.text = "${testClient.firstname} ${testClient.lastname}"

        productRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val repository = AppRepository(application)
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                if(intent.extras != null){
                    repository.deleteFromCart(intent.extras?.getParcelable(DELETE_CART_EXTRA, ProductDTO::class.java)!!)
                }

                val productList = repository.getCart()
                val productAdapter = ProductCartAdapter(productList)
                productRecycler.adapter = productAdapter
            }
        }
    }
}