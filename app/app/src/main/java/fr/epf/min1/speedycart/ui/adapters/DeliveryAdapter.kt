package fr.epf.min1.speedycart.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Delivery

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

        val addressTextView = view.findViewById<TextView>(R.id.delivery_card_address_textview)
        addressTextView.text = delivery.id.toString()

        val productNumberTextView = view.findViewById<TextView>(R.id.delivery_card_product_number_textview)
        productNumberTextView.text = delivery.fee.toString()
    }
}