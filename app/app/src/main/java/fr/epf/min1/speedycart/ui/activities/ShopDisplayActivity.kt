package fr.epf.min1.speedycart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.data.getCompleteAddress
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.adapters.ProductAdapter
import fr.epf.min1.speedycart.ui.adapters.SHOP_EXTRA
import fr.epf.min1.speedycart.ui.adapters.ShopAdapter
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.runBlocking

private const val TAG = "ShopDisplayActivity"

class ShopDisplayActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_display)

        val titleTextView = findViewById<TextView>(R.id.shop_display_title_textview)
        val addressTextView = findViewById<TextView>(R.id.shop_display_address_textview)
        val descriptionTextView = findViewById<TextView>(R.id.shop_display_description_textView)

        intent.extras?.apply {
            val shop = this.getParcelable(SHOP_EXTRA, Shop::class.java)
            shop?.apply {
                titleTextView.text = this.name
                addressTextView.text = this.getCompleteAddress()
                descriptionTextView.text = this.description

                this.id?.let { setProductRecyclerView(it) }
            }
        }

        // put navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.shop_display_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private fun setProductRecyclerView(id: Long) {
        var productRecyclerView =
            findViewById<RecyclerView>(R.id.shop_display_products_recyclerview)
        productRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                val response = clientService.getProductsByShop(id)
                if (response.isSuccessful && response.body() != null) {
                    val productList = response.body()!!
                    Log.d(TAG, "$productList")
                    val productAdapter = ProductAdapter(productList)
                    productRecyclerView.adapter = productAdapter
                } else {
                    Log.d(TAG, "call for product list is empty or unsuccessful")
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }
}