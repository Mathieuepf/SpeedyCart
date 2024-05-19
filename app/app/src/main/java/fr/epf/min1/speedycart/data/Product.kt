package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Product(
    val productId: Long,
    val name: String,
    val unitPrice: Double,
    val description: String,
    val stock: Int,
    val activeSince: Date?,
    val disableSince: Date?,
    val weight: Double,
    val sizes: Double,
    val forAdults: Boolean,
    val shop: Shop
) : Parcelable{
    companion object{
        fun generateListProduct(): List<Product>{
            val productList = ArrayList<Product>()
            productList.add(
                Product(
                1,
                "Pâtes Panzani",
                1.24,
                "Ceci est une description",
                50,
                    Date(1644677500000),
                    null,
                    500.0,
                    1.0,
                    false,
                    Shop.generate1Shop()
            ))
            productList.add(
                Product(
                    2,
                    "Riz",
                    0.98,
                    "Ceci est une description",
                    50,
                    Date(1644677500000),
                    null,
                    500.0,
                    1.0,
                    false,
                    Shop.generate1Shop()
                ))
            productList.add(
                Product(
                    3,
                    "Nouilles",
                    0.85,
                    "Ceci est une description",
                    70,
                    Date(1644677500000),
                    null,
                    250.0,
                    0.5,
                    false,
                    Shop.generate1Shop()
                ))
            productList.add(
                Product(
                    4,
                    "Lait en brique",
                    0.74,
                    "Ceci est une description",
                    40,
                    Date(1644677500000),
                    null,
                    1000.0,
                    1.0,
                    false,
                    Shop.generate1Shop()
                ))
            productList.add(
                Product(
                    5,
                    "Biscuit pépito",
                    1.52,
                    "Ceci est une description",
                    80,
                    Date(1644677500000),
                    null,
                    200.0,
                    0.3,
                    false,
                    Shop.generate1Shop()
                ))
            productList.add(
                Product(
                    6,
                    "Paquet de farine",
                    0.50,
                    "Ceci est une description",
                    100,
                    Date(1644677500000),
                    null,
                    500.0,
                    1.0,
                    false,
                    Shop.generate1Shop()
                ))
            return productList.toList()
        }
    }
}