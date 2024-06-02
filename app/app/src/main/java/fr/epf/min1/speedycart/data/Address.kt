package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val id: Long?,
    val number: String,
    val road: String,
    val city: String,
    val addInfo: String?
) : Parcelable {
    companion object {
        fun generate1Address() = Address(
            1,
            "10",
            "Rue Monge",
            "Montpelier",
            null
        )
    }
}