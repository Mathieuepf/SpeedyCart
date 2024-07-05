package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Admin(
    val id: Long?,
    val description: String
) : Parcelable {
    companion object {
        fun generate1Admin() =
            Admin(
                null,
                "description"
            )
    }
}