package fr.epf.min1.speedycart.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.ui.activities.ClientAccountActivity

class ShopViewHolder(item: View) : RecyclerView.ViewHolder(item)

const val SHOP_EXTRA = "shop"

class ShopAdapter(val shops: List<Shop>): RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.shop_main_view, parent, false)
        return ShopViewHolder(view)
    }

    override fun getItemCount() = shops.size

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = shops[position]
        val view = holder.itemView

        val nameTextView = view.findViewById<TextView>(R.id.name_shop_card_textview)
        nameTextView.text = shop.name

        val adressTextView = view.findViewById<TextView>(R.id.adress_shop_card_textview)
        adressTextView.text = "${shop.address.number} ${shop.address.road} ${shop.address.postalCode} ${shop.address.city}"

        val shopCard = view.findViewById<CardView>(R.id.shop_card_cardview)
        shopCard.click {
            with(it.context){//goal Activity will be ShopActivity
                val intent = Intent(this, ClientAccountActivity::class.java)
                intent.putExtra(SHOP_EXTRA, shop)
                startActivity(intent)
            }
        }
    }
}