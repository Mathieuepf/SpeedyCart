package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Client(
    val id: Long?,
    val firstname: String,
    val lastname: String,
    val activeSince: LocalDateTime?,
    val disableSince: LocalDateTime?,
    val dateOfBirth: LocalDateTime,
) : Parcelable {
    companion object {
        fun generate1Client() = Client(
            45,
            "John2",
            "Doe2",
            null,
            null,
            LocalDateTime.of(1995, 6, 22, 0, 0)
        )
    }
}
