package fr.epf.min1.speedycart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.ui.adapters.PRODUCT_EXTRA

private const val TAG = "ProductDisplayActivity"

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ProductDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_display)

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
                priceTextView.text = "${this.unitPrice}€"
                weightTextView.text = "${this.weight}g"
                shopNameTextView.text = this.shop.name
                descriptionTextView.text = this.description
            }
        }


    }
}