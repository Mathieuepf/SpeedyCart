package fr.epf.min1.speedycart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.ui.adapters.ProductAdapter
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.ui.adapters.ShopAdapter
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.LoginActivity
import fr.epf.min1.speedycart.ui.activities.ProductListFragment
import fr.epf.min1.speedycart.ui.activities.SignupActivity
import kotlinx.coroutines.runBlocking

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var shopRecyclerView: RecyclerView
    lateinit var productRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.main_search_button)
        val userDetailsButton = findViewById<ImageButton>(R.id.main_user_details_imagebutton)
        val shopCartButton = findViewById<ImageButton>(R.id.main_shop_cart_imagebutton)

        // fetch shop
        fetchShopInfo()

        // fetch product
        fetchProductInfo()
        val shopList = Shop.generateListShop()
        Log.d("main", shopList.toString())
        val adapter = ShopAdapter(shopList)
        shopRecyclerView.adapter = adapter

        /*productRecyclerView = findViewById<RecyclerView>(R.id.main_products_recyclerview)
        productRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val productList = Product.generateListProduct()
        Log.d("main", productList.toString())
        val prodadapter = ProductAdapter(productList)
        productRecyclerView.adapter = prodadapter*/

        /*if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ProductListFragment>(R.id.main_product_fragmentcontainerview)
            }
        }*/

        val fragment = ProductListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_product_fragmentcontainerview, fragment)
        transaction.commit()

        loginButton.click {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        userDetailsButton.click {
            val intent = Intent(this, ClientAccountActivity::class.java)
            startActivity(intent)
        }

        shopCartButton.click { //goal Activity will change to CartActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun fetchShopInfo() {
        shopRecyclerView = findViewById<RecyclerView>(R.id.main_shop_recyclerview)
        shopRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                val response = clientService.getShops()
                if (response.isSuccessful && response.body() != null) {
                    val shopList = response.body()!!
                    Log.d(TAG, "$shopList")
                    val adapter = ShopAdapter(shopList)
                    shopRecyclerView.adapter = adapter
                } else {
                    Log.d(TAG, "call for shop list is empty or unsuccessful")
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    private fun fetchProductInfo() {
        productRecyclerView = findViewById<RecyclerView>(R.id.main_products_recyclerview)
        productRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                val response = clientService.getProducts()
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