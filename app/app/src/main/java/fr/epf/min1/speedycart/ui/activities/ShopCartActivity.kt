package fr.epf.min1.speedycart.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sqip.Callback
import sqip.CardEntry
import sqip.CardEntry.DEFAULT_CARD_ENTRY_REQUEST_CODE
import sqip.CardEntryActivityResult

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

        paymentButton.setOnClickListener {
            CardEntry.startCardEntryActivity(this, true, DEFAULT_CARD_ENTRY_REQUEST_CODE)
        }

        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.cart_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        CardEntry.handleActivityResult(data, object : Callback<CardEntryActivityResult> {
            override fun onResult(result: CardEntryActivityResult) {
                when {
                    result.isSuccess() -> {
                        val cardResult = result.getSuccessValue()
                        val card = cardResult.card
                        val nonce = cardResult.nonce
                        Log.d("ShopCartActivity", "paiement accepté")
                    }
                    result.isCanceled() -> {
                        Log.d("ShopCartActivity", "paiement annulé")
                    }
                }
            }
        })
    }
}