package fr.epf.min1.speedycart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.ui.viewmodels.ClientUiState
import fr.epf.min1.speedycart.ui.viewmodels.ClientViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.ProductAdapter
import fr.epf.min1.speedycart.data.Shop
import fr.epf.min1.speedycart.data.ShopAdapter
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.LoginActivity
import fr.epf.min1.speedycart.ui.activities.SignupActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var shopRecyclerView: RecyclerView
    lateinit var productRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.main_login_button)
        val userDetailsButton = findViewById<ImageButton>(R.id.main_user_details_imagebutton)
        val shopCartButton = findViewById<ImageButton>(R.id.main_shop_cart_imagebutton)

        shopRecyclerView = findViewById<RecyclerView>(R.id.main_shop_recyclerview)
        shopRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val shopList = Shop.generateListShop()
        Log.d("main", shopList.toString())
        val adapter = ShopAdapter(shopList)
        shopRecyclerView.adapter = adapter

        productRecyclerView = findViewById<RecyclerView>(R.id.main_products_recyclerview)
        productRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val productList = Product.generateListProduct()
        Log.d("main", productList.toString())
        val prodadapter = ProductAdapter(productList)
        productRecyclerView.adapter = prodadapter

        loginButton.click {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        userDetailsButton.click {
            val intent = Intent(this,ClientAccountActivity::class.java)
            startActivity(intent)
        }

        shopCartButton.click { //goal Activity will change to CartActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}