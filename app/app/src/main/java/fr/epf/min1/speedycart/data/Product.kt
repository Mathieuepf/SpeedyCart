package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Product(
    val id: Long?,
    val name: String,
    val unitPrice: Double,
    val description: String?,
    val stock: Int,
    val activeSince: LocalDateTime?,
    val disableSince: LocalDateTime?,
    val weight: Double,
    val sizes: Double,
    val forAdults: Boolean,
    val shop: Shop
) : Parcelable {
    companion object {
        fun generateListProduct(nb: Int = 10): List<Product> =
            (1..nb).map {
                Product(
                    it.toLong(),
                    "Pates Panza $it",
                    1.24,
                    null,
                    50 + it,
                    null,
                    null,
                    500.0,
                    1.0,
                    false,
                    Shop.generate1Shop()
                )
            }
    }
}