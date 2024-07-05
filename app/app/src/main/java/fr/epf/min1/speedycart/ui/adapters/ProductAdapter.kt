package fr.epf.min1.speedycart.ui.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Product
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity
import fr.epf.min1.speedycart.ui.activities.ProductDisplayActivity

class ProductViewHolder(item: View): RecyclerView.ViewHolder(item)

const val PRODUCT_EXTRA = "product"

class ProductAdapter(val products: List<Product>): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_main_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val view = holder.itemView

        val nameTextView = view.findViewById<TextView>(R.id.name_product_card_textview)
        nameTextView.text = product.name

        val priceTextView = view.findViewById<TextView>(R.id.price_product_card_textview)
        priceTextView.text = String.format("%.2f", product.unitPrice)+" €"

        val weightTextView = view.findViewById<TextView>(R.id.weight_product_card_textview)
        weightTextView.text = String.format("%.2f", product.weight)+"Kg"

        val shopNameTextView = view.findViewById<TextView>(R.id.shop_name_product_card_textview)
        shopNameTextView.text = product.shop.name

        val productCard = view.findViewById<CardView>(R.id.product_card_cardview)
        productCard.click {
            with(it.context){
                val intent = Intent(this, ProductDisplayActivity::class.java)
                intent.putExtra(fr.epf.min1.speedycart.ui.adapters.PRODUCT_EXTRA, product)
                startActivity(intent)
            }
        }
    }
}