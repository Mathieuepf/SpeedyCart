package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class DeliveryPerson(
    val id: Long?,
    val firstname: String,
    val lastname: String,
    val vehicle: String,
    val dateOfBirth: LocalDateTime,
    val activeSince: LocalDateTime?,
    val disableSince: LocalDateTime?,
    val address: Address
) : Parcelable {
    companion object {
        fun generate1DeliveryPerson() = DeliveryPerson(
            1,
            "Jean",
            "Dupont",
            "car",
            LocalDateTime.of(1995, 6, 22, 0, 0),
            null,
            null,
            Address.generate1Address()
        )
    }
}