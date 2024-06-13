package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Order(
    val id: Long?,
    val orderAt: LocalDateTime,
    val payed: Boolean,
    val client: Client,
    val delivery: Delivery,
    val shipTo: Address,
    val chargeTo: Address
): Parcelable
