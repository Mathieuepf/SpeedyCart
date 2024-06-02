package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long?,
    val mail: String,
    val password: String,
    val client: Client?,
    val shop: Shop?,
    val deliveryPerson: DeliveryPerson?,
    val admin: Admin?
) : Parcelable {
    companion object {
        fun generate1User() = User(
            1,
            "jean.dupond@gmail.com",
            "password123",
            Client.generate1Client(),
            null,
            null,
            null
        )
    }
}