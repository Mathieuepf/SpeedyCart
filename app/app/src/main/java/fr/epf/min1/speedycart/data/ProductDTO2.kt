package fr.epf.min1.speedycart.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "productdto")
data class ProductDTO2(
    @PrimaryKey
    val product: Product,
    var quantity: Int
) : Parcelable
