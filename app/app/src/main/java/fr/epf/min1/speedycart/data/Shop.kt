package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Shop(
    val id: Long?,
    val name: String,
    val description: String?,
    val activeSince: LocalDateTime?,
    val disableSince: LocalDateTime?,
    val siret: String,
    val address: Address
) : Parcelable {
    companion object {
        fun generate1Shop() = Shop(
            1,
            "Supermarket",
            null,
            null,
            null,
            "81858249600027",
            Address.generate1Address()
        )

        fun generateListShop(nb: Int = 10): List<Shop> =
            (1..nb).map {
                Shop(
                    it.toLong(),
                    "Supermarket $it",
                    null,
                    null,
                    null,
                    "81858249600027",
                    Address.generate1Address()
                )
            }
    }
}