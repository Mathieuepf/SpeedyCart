package fr.epf.min1.speedycart.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.click
import fr.epf.min1.speedycart.data.getCompleteAddress
import fr.epf.min1.speedycart.ui.activities.DeliveryDetailsActivity

class DeliveryViewHolder(item: View) : RecyclerView.ViewHolder(item)

const val DELIVERY_EXTRA = "delivery"
private const val TAG = "DeliveryAdapter"

class DeliveryAdapter(val deliveries: List<OrderDTO>) : RecyclerView.Adapter<DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.delivery_card_view, parent, false)
        return DeliveryViewHolder(view)
    }

    override fun getItemCount() = deliveries.size

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val orderDTO = deliveries[position]
        val view = holder.itemView

        // fill in all information
        setInfoOnCard(orderDTO, view)

        val deliveryCard = view.findViewById<CardView>(R.id.delivery_card_cardview)
        deliveryCard.click {
            with(it.context) {
                val intent = Intent(this, DeliveryDetailsActivity::class.java)
                intent.putExtra(DELIVERY_EXTRA, orderDTO)
                startActivity(intent)
            }
        }
    }

    private fun setInfoOnCard(orderDTO: OrderDTO, view: View) {
        val product = orderDTO.products[0].product
        val shop = product.shop
        val addressTextView = view.findViewById<TextView>(R.id.delivery_shop_address_textview)
        addressTextView.text = shop.getCompleteAddress()

        val nameShopTextView = view.findViewById<TextView>(R.id.delivery_shop_name_textview)
        nameShopTextView.text = shop.name

        val productNumberTextView =
            view.findViewById<TextView>(R.id.delivery_product_number_textview)
        productNumberTextView.text = "nb de produit: ${orderDTO.products.size}"

        val priceTextView = view.findViewById<TextView>(R.id.delivery_price_textview)
        priceTextView.text = String.format("%.2f", orderDTO.order.delivery.fee / 20) + " €"
    }
}