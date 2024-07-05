package fr.epf.min1.speedycart.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "orderdto")
data class OrderDTO(
    @PrimaryKey
    val order: Order,
    val products: List<ProductDTO2>
) : Parcelable
