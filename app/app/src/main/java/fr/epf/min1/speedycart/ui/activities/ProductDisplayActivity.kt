package fr.epf.min1.speedycart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.ui.adapters.PRODUCT_EXTRA
import fr.epf.min1.speedycart.ui.fragments.NavigationBarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ProductDisplayActivity"

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ProductDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_display)

        setInfoOnPage()

        // put navbar
        if (savedInstanceState == null) { // check navbar not already created
            supportFragmentManager.beginTransaction()
                .replace(R.id.product_display_navbar_fragment_container, NavigationBarFragment())
                .commit()
        }
    }

    private fun setInfoOnPage() {
        val nameTextView = findViewById<TextView>(R.id.product_display_name_textview)
        val priceTextView = findViewById<TextView>(R.id.product_display_price_textview)
        val weightTextView = findViewById<TextView>(R.id.product_display_weight_textview)
        val shopNameTextView = findViewById<TextView>(R.id.product_display_shop_name_textview)
        val descriptionTextView = findViewById<TextView>(R.id.product_display_description_textview)
        val addButton = findViewById<Button>(R.id.product_display_add_button)

        intent.extras?.apply {
            val product = this.getParcelable(PRODUCT_EXTRA, Product::class.java)
            product?.apply {
                nameTextView.text = this.name
                priceTextView.text = String.format("%.2f", this.unitPrice) + " €"
                weightTextView.text = String.format("%.2f", this.weight) + " g"
                shopNameTextView.text = this.shop.name
                descriptionTextView.text = this.description

                addButton.setOnClickListener {
                    val productDTO = this.id?.let { it1 ->
                        ProductDTO(
                            it1,
                            this.name,
                            this.unitPrice,
                            this.weight,
                            this.sizes,
                            this.shop.name
                        )
                    }
                    Log.d(TAG, "productDTO créé")

                    val repository = AppRepository(application)
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.addToCart(productDTO!!)
                            //shortToast()
                            Log.d(TAG, "add effectué")
                        }
                    }
                }
            }
        }
    }

    private fun shortToast() {
        Toast.makeText(
            this@ProductDisplayActivity,
            "Produit ajouté", Toast.LENGTH_SHORT
        ).show()
    }
}