package fr.epf.min1.speedycart.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.OrderDTO
import fr.epf.min1.speedycart.data.getCompleteName

class ShopOrderViewHolder(item: View) : RecyclerView.ViewHolder(item)

const val SHOP_ORDER_EXTRA = "shop_order"
private const val TAG = "ShopOrderAdapter"

class ShopOrderAdapter(val orders: List<OrderDTO>) : RecyclerView.Adapter<ShopOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopOrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.shop_order_view, parent, false)
        return ShopOrderViewHolder(view)
    }

    override fun getItemCount() = orders.size

    override fun onBindViewHolder(holder: ShopOrderViewHolder, position: Int) {
        val orderDTO = orders[position]
        val view = holder.itemView

        // fill in all information
        setInfoOnCard(orderDTO, view)

        /*val deliveryCard = view.findViewById<CardView>(R.id.delivery_card_cardview)
        deliveryCard.click {
            with(it.context) {
                val intent = Intent(this, DeliveryDetailsActivity::class.java)
                intent.putExtra(SHOP_ORDER_EXTRA, orderDTO)
                startActivity(intent)
            }
        }*/
    }


    private fun setInfoOnCard(orderDTO: OrderDTO, view: View) {
        val orderAtTextView = view.findViewById<TextView>(R.id.shop_order_order_at_textview)
        orderAtTextView.text =
            "Commandé à ${orderDTO.order.orderAt.hour} : ${orderDTO.order.orderAt.minute}"

        val nameClientTextView = view.findViewById<TextView>(R.id.shop_order_client_name_textview)
        nameClientTextView.text = orderDTO.order.client.getCompleteName()

        val productNumberTextView =
            view.findViewById<TextView>(R.id.shop_order_product_number_textview)
        productNumberTextView.text = "nb de produit: ${orderDTO.products.size}"

        val priceTextView = view.findViewById<TextView>(R.id.shop_order_price_textview)
        priceTextView.text = String.format("%.2f", orderDTO.order.delivery.fee / 20 * 19) + " €"
    }
}