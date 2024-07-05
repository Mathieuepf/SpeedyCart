package fr.epf.min1.speedycart.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey
    val id: Long,
    val mail: String,
    val password: String,
    val typeUser: TypeUser,
    val typeUserId: Long
) : Parcelable

@Parcelize
enum class TypeUser : Parcelable {
    CLIENT, ADMIN, DELIVERYPERSONNE, SHOP
}