package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDTO(
    val email: String,
    val password: String
) : Parcelable
