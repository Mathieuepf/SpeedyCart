package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Delivery(
    val id: Long?,
    val fee: Double,
    val arriveAt: LocalDateTime,
    val got: Boolean,
    val prepared: Boolean,
    val accepted: Boolean,
    val delivered: Boolean,
    val disable: Boolean,
    val deliveryPerson: DeliveryPerson?
): Parcelable {

    companion object{
        fun generateDeliveries(nb: Int = 4): List<Delivery> =
            (1..nb).map {
                Delivery(
                    it.toLong(),
                    it.toDouble()*10,
                    LocalDateTime.of(2024,6,17,20,26),
                    true,
                    true,
                    false,
                    false,
                    false,
                    null
                )
            }
    }
}
