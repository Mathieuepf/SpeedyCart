package fr.epf.min1.speedycart.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Delivery
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.ui.activities.DeliveryDetailsActivity

class DeliveryViewHolder(item: View): RecyclerView.ViewHolder(item)

const val DELIVERY_EXTRA = "delivery"

class DeliveryAdapter(val deliveries: List<Delivery>): RecyclerView.Adapter<DeliveryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.delivery_card_view, parent, false)
        return DeliveryViewHolder(view)
    }

    override fun getItemCount() = deliveries.size

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val delivery = deliveries[position]
        val view = holder.itemView

        val addressTextView = view.findViewById<TextView>(R.id.delivery_shop_address_textview)
        addressTextView.text = delivery.id.toString()

        val productNumberTextView = view.findViewById<TextView>(R.id.delivery_shop_name_textview)
        productNumberTextView.text = delivery.fee.toString()

        val deliveryCard = view.findViewById<CardView>(R.id.delivery_card_cardview)
        deliveryCard.click {
            with(it.context){
                val intent = Intent(this, DeliveryDetailsActivity::class.java)
                intent.putExtra(DELIVERY_EXTRA, delivery)
                startActivity(intent)
            }
        }
    }
}