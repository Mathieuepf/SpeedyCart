package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val adressId: Long,
    val number: String,
    val road: String,
    val postalCode: String,
    val city: String,
    val addInfo: String?
) : Parcelable {
    companion object{
        fun generate1Address() = Address(
            1,
            "10",
            "Rue Monge",
            "34070",
            "Montpellier",
            null
        )
    }
}