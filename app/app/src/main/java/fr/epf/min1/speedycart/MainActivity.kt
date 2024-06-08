package fr.epf.min1.speedycart

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.fragments.EmptyProductMessageFragment
import fr.epf.min1.speedycart.ui.fragments.EmptyShopMessageFragment
import fr.epf.min1.speedycart.ui.fragments.ProductListFragment
import fr.epf.min1.speedycart.ui.fragments.ShopListFragment
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.LoginActivity
import fr.epf.min1.speedycart.ui.activities.ShopCartActivity
import fr.epf.min1.speedycart.ui.activities.SignupActivity
import kotlinx.coroutines.runBlocking

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.main_search_button)
        loginButton.click {
            TODO("call api to find a shop")
        }

        // Initialise shop and product info
        initProductInfo()
        initShopInfo()

        // call navBar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private fun fetchShopInfo(): List<Shop> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)
        // fetch products
        fetchProductInfo()

        return runBlocking {
            try {
                val response = clientService.getShops()
                if (response.isSuccessful && response.body() != null) {
                    val shopList = response.body()!!
                    Log.d(TAG, "$shopList")
                    shopList
                } else {
                    Log.d(TAG, "call for shop list is empty or unsuccessful")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                emptyList()
            }
        }
    }

    private fun fetchProductInfo(): List<Product> {
        val clientService = Retrofit.getInstance().create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    val productList = response.body()!!
                    Log.d(TAG, "$productList")
                    productList
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

    private fun initShopInfo() {
        val shopList = fetchShopInfo()

        if (shopList.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_shop_fragment_container, EmptyShopMessageFragment())
                .commit()
        } else {
            val fragment = ShopListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_shop_fragment_container, fragment)
                .commit()
            fragment.setShopList(shopList)
        }
    }

    private fun initProductInfo() {
        val productList = fetchProductInfo()

        if (productList.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_product_fragment_container, EmptyProductMessageFragment())
                .commit()
        } else {
            val fragment = ProductListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_product_fragment_container, fragment)
                .commit()
            fragment.setProductList(productList)
        }
    }
}