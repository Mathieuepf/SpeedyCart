package fr.epf.min1.speedycart.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.ProductDTO2

class ProductDtoViewHolder(item: View) : RecyclerView.ViewHolder(item)


class ProductDtoAdapter(val productDtos: List<ProductDTO2>) :
    RecyclerView.Adapter<ProductDtoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDtoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_dto_list_view, parent, false)
        return ProductDtoViewHolder(view)
    }

    override fun getItemCount() = productDtos.size

    override fun onBindViewHolder(holder: ProductDtoViewHolder, position: Int) {
        val productDto = productDtos[position]
        val view = holder.itemView

        val nameTextView =
            view.findViewById<TextView>(R.id.shop_order_details_product_name_textview)
        nameTextView.text = productDto.product.name

        val quantityTextView =
            view.findViewById<TextView>(R.id.shop_order_details_quantity_textview)
        quantityTextView.text = "quantité: ${productDto.quantity}"

    }
}