package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class DeliveryPerson(
    val deliveryPersonId: Long,
    val firstName: String,
    val lastName: String,
    val vehicle: String,
    val dob: LocalDateTime,
    val activeSince: LocalDateTime,
    val disableSince: LocalDateTime?,
    val address: Address
) : Parcelable{
    companion object{
        fun generate1DeliveryPerson() = DeliveryPerson(
            1,
            "Jean",
            "Dupond",
            "Voiture",
            LocalDateTime.of(1995, 6, 22, 0, 0),
            LocalDateTime.of(2022, 2, 10, 16, 56),
            null,
            Address.generate1Address()
        )
    }
}