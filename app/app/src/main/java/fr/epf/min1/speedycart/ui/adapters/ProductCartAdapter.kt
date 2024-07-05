package fr.epf.min1.speedycart.ui.adapters

import android.content.Intent
import android.app.Activity
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.localstorage.AppRepository
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.ShopCartActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.impl.Log
import org.w3c.dom.Text

class ProductCartViewHolder(item: View): RecyclerView.ViewHolder(item)

const val PRODUCT_CART_EXTRA = "product"
const val DELETE_CART_EXTRA = "todelete"

class ProductCartAdapter(val products: List<ProductDTO>): RecyclerView.Adapter<ProductCartViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_cart_view, parent, false)
        return ProductCartViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductCartViewHolder, position: Int) {
        val product = products[position]
        val view = holder.itemView

        val nameTextView = view.findViewById<TextView>(R.id.name_product_cart_card_textview)
        nameTextView.text = product.name

        val priceTextView = view.findViewById<TextView>(R.id.price_product_cart_card_textview)
        priceTextView.text = "${String.format("%.2f", product.unitPrice)}€"

        val weightTextView = view.findViewById<TextView>(R.id.weight_product_cart_card_textview)
        weightTextView.text = "${String.format("%.2f", product.weight)}g"

        val shopNameTextView = view.findViewById<TextView>(R.id.shop_name_product_cart_card_textview)
        shopNameTextView.text = product.shopName

        val productCartCard = view.findViewById<CardView>(R.id.product_cart_card_cardview)
        productCartCard.click {
            with(it.context){
                val intent = Intent(this, ClientAccountActivity::class.java)
                intent.putExtra(PRODUCT_CART_EXTRA, product)
                startActivity(intent)
            }
        }

        val deleteButton = view.findViewById<ImageButton>(R.id.delete_button_product_cart_card)
        deleteButton.click {
            with(it.context){
                val intent = Intent(this, ShopCartActivity::class.java)
                intent.putExtra(DELETE_CART_EXTRA, product)
                startActivity(intent)
            }
        }
    }
}