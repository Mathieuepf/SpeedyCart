package fr.epf.min1.speedycart.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.security.cert.CertStore

@Parcelize
@Entity(tableName = "product")
data class ProductDTO(
    @PrimaryKey
    val id: Long,
    val name: String,
    val unitPrice: Double,
    val weight: Double,
    val sizes: Double,
    val shopName: String
): Parcelable